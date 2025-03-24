INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('bfb8ad07-c8f8-42ff-8746-2b09de9b7f2c', 'HAN', 'Noi Bai International Airport', 'Hanoi', 'Vietnam');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('d67263f2-4556-485d-bcc8-4fa21a8ecd9c', 'SGN', 'Tan Son Nhat International Airport', 'Ho Chi Minh City', 'Vietnam');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('8e0e4ae8-f512-4ba5-9ff0-53765ad6cb6a', 'DAD', 'Da Nang International Airport', 'Da Nang', 'Vietnam');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('ff5c16ad-5205-474c-84d6-a022bf621640', 'CXR', 'Cam Ranh International Airport', 'Nha Trang', 'Vietnam');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('4ea5702e-1eb1-4285-b188-013db96f8dd2', 'PQC', 'Phu Quoc International Airport', 'Phu Quoc', 'Vietnam');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('617e5c37-d4c5-4d47-ad4e-a571a2436e9c', 'HUI', 'Phu Bai International Airport', 'Hue', 'Vietnam');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('46eca2df-8ad3-472c-9988-47a2cf408ea9', 'VCA', 'Can Tho International Airport', 'Can Tho', 'Vietnam');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('8e9e8210-8c76-48ae-9bae-794a438a6226', 'VII', 'Vinh Airport', 'Vinh', 'Vietnam');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('be1489b8-5a86-4e5f-baa6-53a5ac035976', 'BMV', 'Buon Ma Thuot Airport', 'Buon Ma Thuot', 'Vietnam');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('95dc4a93-2639-48a6-8e3d-73f100cbe8c9', 'DLI', 'Lien Khuong Airport', 'Da Lat', 'Vietnam');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('a2b08c9c-b8a7-487f-ba14-5b6cfb5f3dee', 'BKK', 'Suvarnabhumi Airport', 'Bangkok', 'Thailand');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('058640d1-abaa-4895-afd6-fcfcd893514f', 'SIN', 'Changi International Airport', 'Singapore', 'Singapore');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('7235093c-53ba-48c0-a595-aac47705e4ae', 'ICN', 'Incheon International Airport', 'Seoul', 'South Korea');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('3258edb3-b86f-4b3a-80b6-c159b9c50d34', 'NRT', 'Narita International Airport', 'Tokyo', 'Japan');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('2547d070-3649-4d7c-99c9-00c048a306fc', 'KIX', 'Kansai International Airport', 'Osaka', 'Japan');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('9c3e2cf1-d25c-4421-86cc-595e9ee32ea3', 'CDG', 'Charles de Gaulle Airport', 'Paris', 'France');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('cb43e9b7-12ca-434f-9253-0ab768de6b31', 'SYD', 'Sydney Kingsford Smith Airport', 'Sydney', 'Australia');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('2ff8c3d0-165e-4b38-b830-3784dff70d8e', 'FRA', 'Frankfurt Airport', 'Frankfurt', 'Germany');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('69d759af-7967-407e-93fb-8378497a6376', 'LHR', 'Heathrow Airport', 'London', 'United Kingdom');
INSERT INTO airports (airport_id, airport_code, airport_name, city, country) VALUES ('aa8be7c5-a3e4-4b31-acbb-4f31190fb75a', 'TPE', 'Taoyuan International Airport', 'Taipei', 'Taiwan');


-- Airlines
INSERT INTO airlines (airline_id, airline_name, airline_code) VALUES 
('f848359c-2ada-47af-a83c-1fa713f671a0', 'Vietnam Airlines', 'VN'),
('b90beace-4afe-4839-bada-87f23c854888', 'Pacific Airlines', 'BL');



-- Aircrafts
INSERT INTO aircrafts (aircraft_id, aircraft_type, total_seats, row_count, seat_per_row) VALUES
('f2090cdf-d1d2-4ee3-96dd-e0724dfe3808', 'Boeing 787-9 Dreamliner', 274, 39, 7),
('87d43521-eb7e-445a-b5d7-30a6cde0f067', 'Airbus A350-900', 305, 43, 7),
('f778b5ac-1c41-4c00-99c0-308396aa5af0', 'Airbus A321neo', 203, 34, 6),
('6ab5e013-4aa9-4234-85a9-348d0d18f8ca', 'Airbus A321ceo', 184, 31, 6),
('8171f645-f27c-42d2-89d6-30a534d81dbb', 'Airbus A320', 180, 30, 6);



-- Seat Layouts
INSERT INTO seat_layouts (layout_id, layout_name, seat_columns) VALUES
('20580ad5-497a-4a65-9628-7d3760d6e554', '1-2-1', 'A,C,D,F'),
('6e8db980-1e06-4d34-bc49-bac8ab4abc7a', '2-2-2', 'A,B,D,E,F,G'),
('86bb46c2-e7c8-4f1c-b81b-3a916a2c63df', '3-3', 'A,B,C,D,E,F'),
('a9bcf6e3-5eb7-4daf-89ae-93ef6346c46a', '3-3-3', 'A,B,C,D,E,F,G,H,J'),
('285923c0-b99c-4672-924c-07c32af270eb', '3-4-3', 'A,B,C,D,E,F,G,H,J,K');



-- Cabins
INSERT INTO cabins (cabin_id, aircraft_id, cabin_class, start_row, end_row, layout_id, cabin_capacity) VALUES
(gen_random_uuid(), 'f2090cdf-d1d2-4ee3-96dd-e0724dfe3808', 'BUSINESS', 1, 6, '20580ad5-497a-4a65-9628-7d3760d6e554', 24),
(gen_random_uuid(), 'f2090cdf-d1d2-4ee3-96dd-e0724dfe3808', 'ECONOMY', 7, 39, 'a9bcf6e3-5eb7-4daf-89ae-93ef6346c46a', 250),
(gen_random_uuid(), '87d43521-eb7e-445a-b5d7-30a6cde0f067', 'BUSINESS', 1, 7, '6e8db980-1e06-4d34-bc49-bac8ab4abc7a', 30),
(gen_random_uuid(), '87d43521-eb7e-445a-b5d7-30a6cde0f067', 'ECONOMY', 8, 43, 'a9bcf6e3-5eb7-4daf-89ae-93ef6346c46a', 275),
(gen_random_uuid(), 'f778b5ac-1c41-4c00-99c0-308396aa5af0', 'ECONOMY', 1, 34, '86bb46c2-e7c8-4f1c-b81b-3a916a2c63df', 203),
(gen_random_uuid(), '8171f645-f27c-42d2-89d6-30a534d81dbb', 'ECONOMY', 1, 30, '86bb46c2-e7c8-4f1c-b81b-3a916a2c63df', 180);



-- Flights
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES
(gen_random_uuid(), 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN123', 'bfb8ad07-c8f8-42ff-8746-2b09de9b7f2c', 'd67263f2-4556-485d-bcc8-4fa21a8ecd9c',
 '2025-04-01 08:30:00', '2025-04-01 10:45:00', 'f2090cdf-d1d2-4ee3-96dd-e0724dfe3808', 274, 1500000.00),
(gen_random_uuid(), 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN456', 'd67263f2-4556-485d-bcc8-4fa21a8ecd9c', '8e0e4ae8-f512-4ba5-9ff0-53765ad6cb6a',
 '2025-04-02 14:00:00', '2025-04-02 15:30:00', '6ab5e013-4aa9-4234-85a9-348d0d18f8ca', 184, 900000.00),
(gen_random_uuid(), 'b90beace-4afe-4839-bada-87f23c854888', 'BL789', 'd67263f2-4556-485d-bcc8-4fa21a8ecd9c', '4ea5702e-1eb1-4285-b188-013db96f8dd2',
 '2025-04-05 11:00:00', '2025-04-05 12:15:00', '8171f645-f27c-42d2-89d6-30a534d81dbb', 180, 650000.00),
(gen_random_uuid(), 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN321', '8e0e4ae8-f512-4ba5-9ff0-53765ad6cb6a', 'bfb8ad07-c8f8-42ff-8746-2b09de9b7f2c',
 '2025-04-03 09:00:00', '2025-04-03 10:30:00', 'f778b5ac-1c41-4c00-99c0-308396aa5af0', 203, 950000.00),
 (gen_random_uuid(), 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN44', 'd67263f2-4556-485d-bcc8-4fa21a8ecd9c', 'bfb8ad07-c8f8-42ff-8746-2b09de9b7f2c',
 '2025-04-03 09:00:00', '2025-04-03 10:30:00', 'f2090cdf-d1d2-4ee3-96dd-e0724dfe3808', 203, 950000.00);
