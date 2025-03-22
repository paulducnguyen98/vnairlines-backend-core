CREATE TABLE airports (
    airport_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    airport_code VARCHAR(10) UNIQUE NOT NULL,  -- Ví dụ: "HAN", "SGN", "JFK"
    airport_name VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL
);

CREATE TABLE airlines (
    airline_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    airline_name VARCHAR(255) NOT NULL,
    airline_code VARCHAR(10) UNIQUE NOT NULL  -- Ví dụ: "VN" (Vietnam Airlines), "QH" (Bamboo Airways)
);

CREATE TABLE aircrafts (
    aircraft_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    aircraft_type VARCHAR(50) UNIQUE NOT NULL,  -- VD: "Boeing 777", "Airbus A320"
    total_seats INT NOT NULL,
    row_count INT NOT NULL,
    seat_per_row INT NOT NULL
);

CREATE TABLE seat_layouts (
    layout_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    layout_name VARCHAR(10) UNIQUE NOT NULL,  -- Ex: '1-2-1', '2-2-2', '3-4-3'
    seat_columns TEXT NOT NULL  -- Stores seat columns, Ex: 'A,C,D,F,H,K'
);

CREATE TYPE cabin_class_type AS ENUM('FIRST', 'BUSINESS', 'ECONOMY');
CREATE TABLE cabins (
    cabin_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    aircraft_id UUID REFERENCES aircrafts(aircraft_id) ON DELETE CASCADE,
    cabin_class cabin_class_type NOT NULL,
    start_row INT NOT NULL,
    end_row INT NOT NULL,
    layout_id UUID REFERENCES seat_layouts(layout_id) NOT NULL,  -- Seat layout for this cabin
    cabin_capacity INT NOT NULL  -- Total seats in this cabin
);


CREATE 	TYPE user_type as ENUM('CUSTOMER', 'GOLD_MEMBER', 'ADMIN');
CREATE TABLE users (
    user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(20) UNIQUE,
    password_hash TEXT NOT NULL,
    user_type user_type NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);
CREATE INDEX idx_users_email ON users(email);
	
CREATE TABLE flights (
    flight_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    airline_id UUID REFERENCES airlines(airline_id),  -- Thay vì lưu tên hãng hàng không
    flight_number VARCHAR(20) UNIQUE NOT NULL,
    departure_airport_id UUID REFERENCES airports(airport_id) NOT NULL, -- Thay vì lưu trực tiếp mã sân bay
    arrival_airport_id UUID REFERENCES airports(airport_id) NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    aircraft_id UUID REFERENCES aircrafts(aircraft_id) NOT NULL, -- Liên kết máy bay
    seat_capacity INT NOT NULL,  -- Lấy từ `aircrafts.total_seats`
    base_price DECIMAL(10,2) NOT NULL  -- Giá cơ bản của chuyến bay
);

CREATE TYPE seat_type AS ENUM('WINDOW', 'AISLE', 'MIDDLE', 'EXIT_ROW');
CREATE TYPE seat_status_type AS ENUM('AVAILABLE', 'BOOKED', 'CHECKED-IN', 'CANCELLED');

CREATE TABLE seat_inventory (
    seat_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    flight_id UUID REFERENCES flights(flight_id) ON DELETE CASCADE,
    cabin_id UUID REFERENCES cabins(cabin_id) NOT NULL,  
    seat_row INT NOT NULL,  
    seat_column CHAR(1) NOT NULL,  
    seat_type seat_type NOT NULL,
    status seat_status_type NOT NULL DEFAULT 'AVAILABLE',
    updated_at TIMESTAMP DEFAULT NOW(),
    UNIQUE (flight_id, seat_row, seat_column)
);

CREATE OR REPLACE FUNCTION generate_seat_inventory()
RETURNS TRIGGER AS $$
DECLARE
    cabin RECORD;
    row_num INT;
    seat_letter TEXT;
    seat_type seat_type;
    layout_columns TEXT[];
BEGIN
    FOR cabin IN
        SELECT * FROM cabins WHERE aircraft_id = (SELECT aircraft_id FROM flights WHERE flight_id = NEW.flight_id)
    LOOP
        SELECT STRING_TO_ARRAY(seat_columns, ',') INTO layout_columns
        FROM seat_layouts WHERE layout_id = cabin.layout_id;

        FOR row_num IN cabin.start_row..cabin.end_row LOOP
            FOREACH seat_letter IN ARRAY layout_columns LOOP
                seat_type := CASE
                    WHEN seat_letter IN ('A', 'K') THEN 'WINDOW'
                    WHEN seat_letter IN ('C', 'D', 'G', 'H') THEN 'MIDDLE'
                    ELSE 'AISLE'
                END;

                INSERT INTO seat_inventory (
                    seat_id, flight_id, cabin_id, seat_row, seat_column, seat_type, status
                ) VALUES (
                    gen_random_uuid(), NEW.flight_id, cabin.cabin_id, row_num, seat_letter, seat_type, 'AVAILABLE'
                );
            END LOOP;
        END LOOP;
    END LOOP;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- Trigger when a flight is created
CREATE TRIGGER trigger_generate_seat_inventory
AFTER INSERT ON flights
FOR EACH ROW EXECUTE FUNCTION generate_seat_inventory();


CREATE TYPE booking_status as ENUM('PENDING', 'COMPLETED', 'FAILED');
CREATE TABLE bookings (
    booking_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(user_id) NULL,  
    contact_first_name VARCHAR(255) NOT NULL, 
	contact_last_name VARCHAR(255) NOT NULL, 	
    contact_email VARCHAR(255) NOT NULL,  
    contact_phone VARCHAR(20) NOT NULL,
    booking_code VARCHAR(20) UNIQUE NOT NULL,  -- Mã đặt chỗ (PNR)
    total_price DECIMAL(10,2) NOT NULL,
    status booking_status NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT NOW()
);
CREATE INDEX idx_bookings_code ON bookings(booking_code);

CREATE TABLE passengers (
    passenger_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    booking_id UUID REFERENCES bookings(booking_id) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NULL,
    phone_number VARCHAR(20) NULL,
    birth_date DATE NULL,
    passport_number VARCHAR(20) NULL,
    is_main_contact BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NOW()
);
CREATE INDEX idx_passengers_email ON passengers(email);

CREATE TYPE ticket_status_type AS ENUM('BOOKED', 'CHECKED-IN', 'CANCELLED');
CREATE TYPE ticket_class_type AS ENUM('ECONOMY', 'BUSINESS', 'FIRST');
CREATE TABLE tickets (
    ticket_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    passenger_id UUID REFERENCES passengers(passenger_id),
    flight_id UUID REFERENCES flights(flight_id),
    ticket_number VARCHAR(30) UNIQUE NOT NULL,
    ticket_class ticket_class_type NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    status ticket_status_type NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
	cancelled_at TIMESTAMP
);

-- Tạo index để tăng tốc độ tìm kiếm vé theo người dùng
CREATE INDEX idx_tickets_passenger ON passengers(passenger_id);

CREATE TYPE payment_status_type AS ENUM('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED');
CREATE TYPE payment_method_type AS ENUM('PAYPAL', 'VNPAY');

CREATE TABLE payments (
    payment_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    booking_id UUID REFERENCES bookings(booking_id),
    amount DECIMAL(10,2) NOT NULL,
    payment_method payment_method_type NOT NULL,
    status payment_status_type NOT NULL,
    transaction_id VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);
CREATE INDEX idx_payments_transaction_id ON payments(transaction_id);

CREATE TABLE check_in (
    check_in_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ticket_id UUID REFERENCES tickets(ticket_id),
    seat_number VARCHAR(5) NOT NULL,
    boarding_pass_url TEXT,
	is_checked_in BOOLEAN DEFAULT FALSE,
    check_in_time TIMESTAMP DEFAULT NOW()
);

