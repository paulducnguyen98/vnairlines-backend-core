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

INSERT INTO membership_tiers (tier_id, tier_name, tier_rank, required_miles, required_flights, benefits)
VALUES
    ('b85f04f9-538a-4c65-a5c8-e0b03b015b36', 'Basic',    1, 0,    0,    'Standard member benefits'),
    ('af49e5ce-3a5d-42a5-a43b-2ab6d9c9d6e6', 'Silver',   2, 10000, 10,  'Priority check-in, 5kg baggage bonus'),
    ('2c9d3d50-8f9c-4d23-84fd-1e390a5ce1f1', 'Gold',     3, 25000, 20,  'Lounge access, bonus miles'),
    ('dbdcd22e-79a6-42dc-b9f1-6a92ae7f350e', 'Titanium', 4, 50000, 40,  'Business class upgrade, priority boarding'),
    ('60bcbb15-8a50-42db-bdb0-071933dd5f79', 'Platinum', 5, 75000, 60,  'Unlimited lounge access, VIP hotline');


--INSERT INTO users (user_id, first_name, last_name, email, phone_number, password_hash, is_admin, is_loyalty_member, created_at)
--VALUES
--('2c9d3d50-8f9c-4d23-84fd-1e390a5ce1f1', 'Nguyen','Van A', 'a@example.com', '0900000001', 'hashed_pw_1', false, true, NOW()),
--('b85f04f9-538a-4c65-a5c8-e0b03b015b36', 'Tran', 'Thi B',  'b@example.com', '0900000002', 'hashed_pw_2', false, true, NOW()),
--('a122cbfc-0627-4c99-bdb9-f7277e98a8a9', 'Le', 'Van C',    'c@example.com', '0900000003', 'hashed_pw_3', false, true, NOW()),
--('af49e5ce-3a5d-42a5-a43b-2ab6d9c9d6e6', 'Pham', 'Thi D',  'd@example.com', '0900000004', 'hashed_pw_4', false, true, NOW()),
--('0f59fc27-49e2-4e1c-a264-2475c45c4909', 'Hoang', 'Van E', 'e@example.com', '0900000005', 'hashed_pw_5', false, true, NOW()),
--('dbdcd22e-79a6-42dc-b9f1-6a92ae7f350e', 'Do', 'Thi F',    'f@example.com', '0900000006', 'hashed_pw_6', false, true, NOW()),
--('4be7c2cb-3485-41f4-b8e6-41f3db041ed5', 'Nguyen', 'Van G','g@example.com', '0900000007', 'hashed_pw_7', false, true, NOW()),
--('f2cdabde-65b1-4a4c-b964-4e70e3cd4ed5', 'Pham', 'Thi H',  'h@example.com', '0900000008', 'hashed_pw_8', false, true, NOW()),
--('e0f06f5f-0f44-4c6f-8c1a-20d778ebd4df', 'Tran', 'Van I',  'i@example.com', '0900000009', 'hashed_pw_9', false, true, NOW()),
--('60bcbb15-8a50-42db-bdb0-071933dd5f79', 'Le', 'Thi J',    'j@example.com', '0900000010', 'hashed_pw_10', false, true, NOW());
INSERT INTO users (
    user_id, first_name, last_name, email, phone_number,
    gender, address, identity_number, identity_issued_date,
    identity_issued_place, created_at
)
VALUES
('2c9d3d50-8f9c-4d23-84fd-1e390a5ce1f1', 'Nguyen', 'Van A', 'a@example.com', '0900000001',
 'MALE', '123 Đường A, Hà Nội', '070098000001', '2020-01-01', 'Hà Nội', NOW()),

('b85f04f9-538a-4c65-a5c8-e0b03b015b36', 'Tran', 'Thi B', 'b@example.com', '0900000002',
 'FEMALE', '456 Đường B, HCM', '070098000002', '2020-01-02', 'TP HCM', NOW()),

('a122cbfc-0627-4c99-bdb9-f7277e98a8a9', 'Le', 'Van C', 'c@example.com', '0900000003',
 'MALE', '789 Đường C, Đà Nẵng', '070098000003', '2020-01-03', 'Đà Nẵng', NOW()),

('af49e5ce-3a5d-42a5-a43b-2ab6d9c9d6e6', 'Pham', 'Thi D', 'd@example.com', '0900000004',
 'FEMALE', '123 Đường D, Cần Thơ', '070098000004', '2020-01-04', 'Cần Thơ', NOW()),

('0f59fc27-49e2-4e1c-a264-2475c45c4909', 'Hoang', 'Van E', 'e@example.com', '0900000005',
 'MALE', '234 Đường E, Hải Phòng', '070098000005', '2020-01-05', 'Hải Phòng', NOW()),

('dbdcd22e-79a6-42dc-b9f1-6a92ae7f350e', 'Do', 'Thi F', 'f@example.com', '0900000006',
 'FEMALE', '345 Đường F, Nha Trang', '070098000006', '2020-01-06', 'Nha Trang', NOW()),

('4be7c2cb-3485-41f4-b8e6-41f3db041ed5', 'Nguyen', 'Van G', 'g@example.com', '0900000007',
 'MALE', '456 Đường G, Huế', '070098000007', '2020-01-07', 'Huế', NOW()),

('f2cdabde-65b1-4a4c-b964-4e70e3cd4ed5', 'Pham', 'Thi H', 'h@example.com', '0900000008',
 'FEMALE', '567 Đường H, Quảng Ninh', '070098000008', '2020-01-08', 'Quảng Ninh', NOW()),

('e0f06f5f-0f44-4c6f-8c1a-20d778ebd4df', 'Tran', 'Van I', 'i@example.com', '0900000009',
 'MALE', '678 Đường I, Lâm Đồng', '070098000009', '2020-01-09', 'Lâm Đồng', NOW()),

('60bcbb15-8a50-42db-bdb0-071933dd5f79', 'Le', 'Thi J', 'j@example.com', '0900000010',
 'FEMALE', '789 Đường J, Bình Định', '070098000010', '2020-01-10', 'Bình Định', NOW());

-- Ví dụ minh hoạ với user_id giả định (bạn cần thay bằng ID thực tế đã insert)
INSERT INTO user_loyalty_profiles (user_id, current_tier_id, total_miles, total_flights, miles_expiry_date, tier_achieved_at)
VALUES
('2c9d3d50-8f9c-4d23-84fd-1e390a5ce1f1', 'b85f04f9-538a-4c65-a5c8-e0b03b015b36', 500, 2, NOW() + interval '12 months', NOW()),
('b85f04f9-538a-4c65-a5c8-e0b03b015b36', 'b85f04f9-538a-4c65-a5c8-e0b03b015b36', 800, 3, NOW() + interval '12 months', NOW()),
('a122cbfc-0627-4c99-bdb9-f7277e98a8a9', 'af49e5ce-3a5d-42a5-a43b-2ab6d9c9d6e6', 11000, 12, NOW() + interval '12 months', NOW()),
('af49e5ce-3a5d-42a5-a43b-2ab6d9c9d6e6', 'af49e5ce-3a5d-42a5-a43b-2ab6d9c9d6e6', 15000, 15, NOW() + interval '12 months', NOW()),
('0f59fc27-49e2-4e1c-a264-2475c45c4909', '2c9d3d50-8f9c-4d23-84fd-1e390a5ce1f1', 26000, 21, NOW() + interval '12 months', NOW()),
('dbdcd22e-79a6-42dc-b9f1-6a92ae7f350e', '2c9d3d50-8f9c-4d23-84fd-1e390a5ce1f1', 30000, 25, NOW() + interval '12 months', NOW()),
('4be7c2cb-3485-41f4-b8e6-41f3db041ed5', 'dbdcd22e-79a6-42dc-b9f1-6a92ae7f350e', 52000, 42, NOW() + interval '12 months', NOW()),
('f2cdabde-65b1-4a4c-b964-4e70e3cd4ed5', 'dbdcd22e-79a6-42dc-b9f1-6a92ae7f350e', 56000, 45, NOW() + interval '12 months', NOW()),
('e0f06f5f-0f44-4c6f-8c1a-20d778ebd4df', '60bcbb15-8a50-42db-bdb0-071933dd5f79', 80000, 65, NOW() + interval '12 months', NOW()),
('60bcbb15-8a50-42db-bdb0-071933dd5f79', '60bcbb15-8a50-42db-bdb0-071933dd5f79', 95000, 80, NOW() + interval '12 months', NOW());
