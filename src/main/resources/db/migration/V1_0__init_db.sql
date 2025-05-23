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


CREATE TABLE users (
    user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20) UNIQUE,
    address VARCHAR(255),
    gender VARCHAR(10),
    identity_number VARCHAR(50),
    identity_issued_date DATE,
    identity_issued_place VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW(),
    password_hash TEXT
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
    citizen_id VARCHAR(20) NULL,
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
    seat_id UUID REFERENCES seat_inventory(seat_id),
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


CREATE TABLE fare_classes (
    fare_class_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ticket_class ticket_class_type NOT NULL UNIQUE,
    base_price DECIMAL(10,2) NOT NULL
);

INSERT INTO fare_classes (fare_class_id, ticket_class, base_price)
VALUES 
    (gen_random_uuid(), 'ECONOMY', 100.00),
    (gen_random_uuid(), 'BUSINESS', 250.00),
    (gen_random_uuid(), 'FIRST', 500.00);

    
CREATE TABLE fare_rules (
    fare_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    flight_id UUID REFERENCES flights(flight_id) ON DELETE CASCADE,
    fare_class_id UUID REFERENCES fare_classes(fare_class_id) ON DELETE CASCADE,
    base_fare DECIMAL(10,2) NOT NULL
);

CREATE TABLE fare_adjustments (
    adjustment_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    flight_id UUID REFERENCES flights(flight_id) ON DELETE CASCADE NOT NULL,
    fare_class_id UUID REFERENCES fare_classes(fare_class_id) NOT NULL,
    discount_percentage DECIMAL(5,2) DEFAULT 0,  -- Ex: -20% for a promo
    surcharge_percentage DECIMAL(5,2) DEFAULT 0,  -- Ex: +10% for peak hours
    start_time TIMESTAMP NOT NULL,  -- When the discount/surcharge starts
    end_time TIMESTAMP NOT NULL     -- When it ends
);

CREATE OR REPLACE FUNCTION generate_fares_for_new_flight()
RETURNS TRIGGER AS $$
DECLARE
    fare_class RECORD;
    departure_airport_id UUID;
    arrival_airport_id UUID;
    base_fare DECIMAL(10,2);
BEGIN
    -- Get departure and arrival airports from the flights table
    SELECT f.departure_airport_id, f.arrival_airport_id 
    INTO departure_airport_id, arrival_airport_id
    FROM flights f WHERE f.flight_id = NEW.flight_id;

    -- Loop through each fare class and insert base fares
    FOR fare_class IN SELECT * FROM fare_classes LOOP
        -- Example base fare logic (you may adjust it)
        base_fare := fare_class.base_price;

        INSERT INTO fare_rules (fare_id, flight_id, fare_class_id, base_fare)
        VALUES (gen_random_uuid(), NEW.flight_id, fare_class.fare_class_id, base_fare);
    END LOOP;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger when a flight is created
CREATE TRIGGER trigger_generate_fares
AFTER INSERT ON flights
FOR EACH ROW EXECUTE FUNCTION generate_fares_for_new_flight();


ALTER TABLE bookings ADD COLUMN trip_reference_id UUID;

ALTER TABLE payments ADD COLUMN trip_reference_id UUID;

ALTER TABLE payments
    ADD CONSTRAINT chk_payment_target CHECK (
        booking_id IS NOT NULL OR trip_reference_id IS NOT NULL
    );

    
    
CREATE TABLE membership_tiers (
    tier_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tier_name VARCHAR(50) UNIQUE NOT NULL,  -- Basic, Silver, Titanium, Gold, Platinum
    tier_rank INT NOT NULL,  -- 1 (Basic) → 5 (Platinum)
    required_miles INT NOT NULL,
    required_flights INT NOT NULL,
    benefits TEXT
);

CREATE TABLE user_loyalty_profiles (
    user_id UUID PRIMARY KEY REFERENCES users(user_id) ON DELETE CASCADE,
    current_tier_id UUID REFERENCES membership_tiers(tier_id),
    membership_code VARCHAR(10) UNIQUE NOT NULL CHECK (membership_code ~ '^[0-9]{10}$'),
    total_miles INT DEFAULT 0,
    total_flights INT DEFAULT 0,
    miles_expiry_date DATE,
    tier_achieved_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE miles_transactions (
    transaction_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(user_id),
    flight_id UUID REFERENCES flights(flight_id),
    miles_change INT NOT NULL,  -- có thể âm nếu trừ dặm
    reason TEXT NOT NULL,  -- VD: “Flight completed”, “Redeemed for ticket”
    transaction_time TIMESTAMP DEFAULT NOW()
);

CREATE TABLE mile_redemptions (
    redemption_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(user_id),
    miles_used INT NOT NULL,
    redeemed_for TEXT,  -- VD: Ticket, Upgrade, Voucher
    related_ticket_id UUID REFERENCES tickets(ticket_id),
    redeemed_at TIMESTAMP DEFAULT NOW()
);