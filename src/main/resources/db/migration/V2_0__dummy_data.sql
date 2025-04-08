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

INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    'd6b8a51d-2fe3-455d-9a85-6cffde18a295', 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN654', 'd67263f2-4556-485d-bcc8-4fa21a8ecd9c', 'ff5c16ad-5205-474c-84d6-a022bf621640',
    '2025-04-07 11:00:00', '2025-04-07 13:00:00',
    'f778b5ac-1c41-4c00-99c0-308396aa5af0', 203, 1717000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    '431bdcec-5013-419a-86af-2880208e6c50', 'b90beace-4afe-4839-bada-87f23c854888', 'BL420', '8e9e8210-8c76-48ae-9bae-794a438a6226', '69d759af-7967-407e-93fb-8378497a6376',
    '2025-04-08 09:00:00', '2025-04-08 10:45:00',
    '8171f645-f27c-42d2-89d6-30a534d81dbb', 180, 2362000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    '734b351a-90fb-4a82-915f-637eec4b29d6', 'b90beace-4afe-4839-bada-87f23c854888', 'BL174', 'a2b08c9c-b8a7-487f-ba14-5b6cfb5f3dee', '46eca2df-8ad3-472c-9988-47a2cf408ea9',
    '2025-04-09 06:00:00', '2025-04-09 08:45:00',
    '6ab5e013-4aa9-4234-85a9-348d0d18f8ca', 184, 753000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    'dbb5452e-0881-4d4b-8d50-caafd71fb04e', 'b90beace-4afe-4839-bada-87f23c854888', 'BL149', 'aa8be7c5-a3e4-4b31-acbb-4f31190fb75a', '4ea5702e-1eb1-4285-b188-013db96f8dd2',
    '2025-04-10 10:00:00', '2025-04-10 13:00:00',
    '8171f645-f27c-42d2-89d6-30a534d81dbb', 180, 1130000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    '1f57defe-8f38-4da5-b3d5-d6072b5b0e87', 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN879', '69d759af-7967-407e-93fb-8378497a6376', 'be1489b8-5a86-4e5f-baa6-53a5ac035976',
    '2025-04-11 11:00:00', '2025-04-11 12:30:00',
    'f2090cdf-d1d2-4ee3-96dd-e0724dfe3808', 274, 1349000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    'c227f51f-ac2c-4850-82ff-71b5e42c3c53', 'b90beace-4afe-4839-bada-87f23c854888', 'BL649', 'cb43e9b7-12ca-434f-9253-0ab768de6b31', '8e0e4ae8-f512-4ba5-9ff0-53765ad6cb6a',
    '2025-04-12 09:00:00', '2025-04-12 11:30:00',
    'f778b5ac-1c41-4c00-99c0-308396aa5af0', 203, 2008000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    '1994a787-4e43-4af8-8f10-68980c839b23', 'b90beace-4afe-4839-bada-87f23c854888', 'BL198', 'aa8be7c5-a3e4-4b31-acbb-4f31190fb75a', '058640d1-abaa-4895-afd6-fcfcd893514f',
    '2025-04-13 08:00:00', '2025-04-13 13:30:00',
    '87d43521-eb7e-445a-b5d7-30a6cde0f067', 305, 2159000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    '38828dab-a3ad-4dfc-95f9-fc34ec554cb6', 'b90beace-4afe-4839-bada-87f23c854888', 'BL523', '3258edb3-b86f-4b3a-80b6-c159b9c50d34', '2ff8c3d0-165e-4b38-b830-3784dff70d8e',
    '2025-04-14 09:00:00', '2025-04-14 10:30:00',
    'f2090cdf-d1d2-4ee3-96dd-e0724dfe3808', 274, 771000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    '5536a34d-d1cc-425b-a457-20ee057217df', 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN577', '69d759af-7967-407e-93fb-8378497a6376', '9c3e2cf1-d25c-4421-86cc-595e9ee32ea3',
    '2025-04-15 07:00:00', '2025-04-15 08:30:00',
    'f2090cdf-d1d2-4ee3-96dd-e0724dfe3808', 274, 1066000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    '299abf1b-c3df-4aae-a25b-2b9544a41096', 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN718', 'ff5c16ad-5205-474c-84d6-a022bf621640', '95dc4a93-2639-48a6-8e3d-73f100cbe8c9',
    '2025-04-16 12:00:00', '2025-04-16 17:30:00',
    '8171f645-f27c-42d2-89d6-30a534d81dbb', 180, 741000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    'dd2930ed-1a75-4da3-8632-ff6da2262d6e', 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN606', '8e9e8210-8c76-48ae-9bae-794a438a6226', 'bfb8ad07-c8f8-42ff-8746-2b09de9b7f2c',
    '2025-04-17 12:00:00', '2025-04-17 16:15:00',
    '6ab5e013-4aa9-4234-85a9-348d0d18f8ca', 184, 1605000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    'b8821c21-dce7-4e6f-8cfe-bb610c4a3fb4', 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN485', '4ea5702e-1eb1-4285-b188-013db96f8dd2', 'be1489b8-5a86-4e5f-baa6-53a5ac035976',
    '2025-04-18 12:00:00', '2025-04-18 15:45:00',
    '8171f645-f27c-42d2-89d6-30a534d81dbb', 180, 964000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    '5bb14ab5-6cd6-4a42-bed2-117244cd10ff', 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN855', '058640d1-abaa-4895-afd6-fcfcd893514f', '7235093c-53ba-48c0-a595-aac47705e4ae',
    '2025-04-19 06:00:00', '2025-04-19 10:00:00',
    'f2090cdf-d1d2-4ee3-96dd-e0724dfe3808', 274, 816000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    '1e4eaf0f-5d68-4902-aa80-5929e5794eef', 'b90beace-4afe-4839-bada-87f23c854888', 'BL782', '4ea5702e-1eb1-4285-b188-013db96f8dd2', '2ff8c3d0-165e-4b38-b830-3784dff70d8e',
    '2025-04-20 12:00:00', '2025-04-20 17:00:00',
    'f778b5ac-1c41-4c00-99c0-308396aa5af0', 203, 1173000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    'a172885d-c983-44a4-a613-2f122e73ff66', 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN772', '617e5c37-d4c5-4d47-ad4e-a571a2436e9c', 'bfb8ad07-c8f8-42ff-8746-2b09de9b7f2c',
    '2025-04-21 11:00:00', '2025-04-21 13:00:00',
    'f2090cdf-d1d2-4ee3-96dd-e0724dfe3808', 274, 1689000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    'b44f6d81-bc02-4098-a6c5-277ec45488e0', 'b90beace-4afe-4839-bada-87f23c854888', 'BL894', '8e9e8210-8c76-48ae-9bae-794a438a6226', 'bfb8ad07-c8f8-42ff-8746-2b09de9b7f2c',
    '2025-04-22 08:00:00', '2025-04-22 09:45:00',
    'f2090cdf-d1d2-4ee3-96dd-e0724dfe3808', 274, 1659000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    'd455870a-8b77-4a70-818d-c0639913beea', 'b90beace-4afe-4839-bada-87f23c854888', 'BL673', '9c3e2cf1-d25c-4421-86cc-595e9ee32ea3', 'd67263f2-4556-485d-bcc8-4fa21a8ecd9c',
    '2025-04-23 06:00:00', '2025-04-23 10:45:00',
    '87d43521-eb7e-445a-b5d7-30a6cde0f067', 305, 2217000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    'e990c819-a75f-41ea-ae0c-aae4e7bf6f2d', 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN668', '4ea5702e-1eb1-4285-b188-013db96f8dd2', '2ff8c3d0-165e-4b38-b830-3784dff70d8e',
    '2025-04-24 08:00:00', '2025-04-24 10:45:00',
    '8171f645-f27c-42d2-89d6-30a534d81dbb', 180, 1175000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    'c9edcbf1-93b5-413d-84d6-ca28be0b52b1', 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN767', 'aa8be7c5-a3e4-4b31-acbb-4f31190fb75a', '7235093c-53ba-48c0-a595-aac47705e4ae',
    '2025-04-25 11:00:00', '2025-04-25 14:30:00',
    '87d43521-eb7e-445a-b5d7-30a6cde0f067', 305, 1735000.0
);
INSERT INTO flights (
    flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
    departure_time, arrival_time, aircraft_id, seat_capacity, base_price
) VALUES (
    '72e8cf81-04f4-427a-8f1f-c9fdc75a7b08', 'f848359c-2ada-47af-a83c-1fa713f671a0', 'VN202', '2547d070-3649-4d7c-99c9-00c048a306fc', '617e5c37-d4c5-4d47-ad4e-a571a2436e9c',
    '2025-04-26 12:00:00', '2025-04-26 14:15:00',
    '6ab5e013-4aa9-4234-85a9-348d0d18f8ca', 184, 848000.0
);
 

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

-- normal users
INSERT INTO users (user_id, first_name, last_name, email, created_at)
VALUES
('b99f824c-67a4-441e-91db-bd9de0c1a100', '', '', 'kde@example.com', NOW()),
('c78be712-157f-4c26-b6d9-60b260a68c31', '', '', 'law@example.com', NOW()),
('f630a93e-b4ad-47e6-b7ef-8e18ed33c591', '', '', 'mgege@example.com', NOW()),
('a2e6c045-e3e2-4f90-8dd1-2a8d6d5a7fbc', '', '', 'ndede@example.com', NOW()),
('0140ce2c-21d2-46cb-912f-4b3920f6df3c', '', '', 'ogege@example.com', NOW()),
('ea13cf65-2eaf-41fd-994c-b2673b4f5fd3', '', '', 'paaea@example.com', NOW()),
('6c1e8b3e-f531-4a89-88b2-67a64e80e6a7', '', '', 'qeeqw@example.com', NOW()),
('48b6e10f-7855-4f9a-984e-9c6a30e50332', '', '', 'rdeguu@example.com', NOW()),
('9bb5361c-fbe6-4aa3-8d34-5a6636efb1f4', '', '', 'swewq@example.com', NOW()),
('e663fa5e-48ad-4a67-b9ee-e5ff3c5bb717', '', '', 'tttweqeqq@example.com', NOW());


INSERT INTO user_loyalty_profiles (
    user_id, current_tier_id, membership_code, total_miles, total_flights, miles_expiry_date, tier_achieved_at
)
VALUES
-- Nguyen Van A - Silver
('2c9d3d50-8f9c-4d23-84fd-1e390a5ce1f1', 'af49e5ce-3a5d-42a5-a43b-2ab6d9c9d6e6', '1234567890', 500, 2, NOW() + INTERVAL '12 months', NOW()),

-- Tran Thi B - Silver
('b85f04f9-538a-4c65-a5c8-e0b03b015b36', 'af49e5ce-3a5d-42a5-a43b-2ab6d9c9d6e6', '2345678901', 800, 3, NOW() + INTERVAL '12 months', NOW()),

-- Le Van C - Titanium
('a122cbfc-0627-4c99-bdb9-f7277e98a8a9', 'dbdcd22e-79a6-42dc-b9f1-6a92ae7f350e', '3456789012', 11000, 12, NOW() + INTERVAL '12 months', NOW()),

-- Pham Thi D - Titanium
('af49e5ce-3a5d-42a5-a43b-2ab6d9c9d6e6', 'dbdcd22e-79a6-42dc-b9f1-6a92ae7f350e', '4567890123', 15000, 15, NOW() + INTERVAL '12 months', NOW()),

-- Hoang Van E - Gold
('0f59fc27-49e2-4e1c-a264-2475c45c4909', '2c9d3d50-8f9c-4d23-84fd-1e390a5ce1f1', '5678901234', 26000, 21, NOW() + INTERVAL '12 months', NOW()),

-- Do Thi F - Gold
('dbdcd22e-79a6-42dc-b9f1-6a92ae7f350e', '2c9d3d50-8f9c-4d23-84fd-1e390a5ce1f1', '6789012345', 30000, 25, NOW() + INTERVAL '12 months', NOW()),

-- Nguyen Van G - Platinum
('4be7c2cb-3485-41f4-b8e6-41f3db041ed5', '60bcbb15-8a50-42db-bdb0-071933dd5f79', '7890123456', 52000, 42, NOW() + INTERVAL '12 months', NOW()),

-- Pham Thi H - Platinum
('f2cdabde-65b1-4a4c-b964-4e70e3cd4ed5', '60bcbb15-8a50-42db-bdb0-071933dd5f79', '8901234567', 56000, 45, NOW() + INTERVAL '12 months', NOW()),

-- Tran Van I - Elite
('e0f06f5f-0f44-4c6f-8c1a-20d778ebd4df', '60bcbb15-8a50-42db-bdb0-071933dd5f79', '9012345678', 80000, 65, NOW() + INTERVAL '12 months', NOW()),

-- Le Thi J - Elite
('60bcbb15-8a50-42db-bdb0-071933dd5f79', '60bcbb15-8a50-42db-bdb0-071933dd5f79', '0123456789', 95000, 80, NOW() + INTERVAL '12 months', NOW());


CREATE OR REPLACE FUNCTION update_loyalty_profile_totals()
RETURNS TRIGGER AS $$
DECLARE
    target_user_id UUID;
BEGIN
    -- Determine the user to update based on the operation type.
    IF (TG_OP = 'DELETE') THEN
        target_user_id := OLD.user_id;
    ELSE
        target_user_id := NEW.user_id;
    END IF;

    UPDATE user_loyalty_profiles
    SET total_miles = (
         SELECT COALESCE(SUM(miles_change), 0)
         FROM miles_transactions
         WHERE user_id = target_user_id
    ),
    total_flights = (
         SELECT COUNT(*)
         FROM miles_transactions
         WHERE user_id = target_user_id
           AND flight_id IS NOT NULL
           AND reason = 'Flight completed'
    )
    WHERE user_id = target_user_id;

    RETURN NULL;  -- For AFTER triggers, the return value is ignored.
END;
$$ LANGUAGE plpgsql;

-- Create the trigger to fire AFTER INSERT, UPDATE, or DELETE on miles_transactions.
CREATE TRIGGER trg_update_loyalty_profile
AFTER INSERT OR UPDATE OR DELETE ON miles_transactions
FOR EACH ROW
EXECUTE FUNCTION update_loyalty_profile_totals();


-- miles transaction 
-- miles transaction 
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('9e1a3a4f-c841-4173-83ac-e488300fa793', '2c9d3d50-8f9c-4d23-84fd-1e390a5ce1f1', '299abf1b-c3df-4aae-a25b-2b9544a41096', 8541, 'Flight completed', '2025-02-25 02:01:39');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('239d35ed-d94c-4af2-96f7-de1b0d43210c', '2c9d3d50-8f9c-4d23-84fd-1e390a5ce1f1', '299abf1b-c3df-4aae-a25b-2b9544a41096', 751, 'Flight completed', '2025-04-29 01:30:12');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('638b4f7c-f2e1-4f1f-a9df-ceea49ed2cf4', '2c9d3d50-8f9c-4d23-84fd-1e390a5ce1f1', 'd455870a-8b77-4a70-818d-c0639913beea', 7954, 'Flight completed', '2024-11-01 02:15:46');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('9463cfa3-91f1-4c86-8718-4ee184d26f45', 'b85f04f9-538a-4c65-a5c8-e0b03b015b36', '5bb14ab5-6cd6-4a42-bed2-117244cd10ff', 911, 'Flight completed', '2025-04-18 17:45:29');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('61361f69-3095-4970-8265-2815762d6df9', 'b85f04f9-538a-4c65-a5c8-e0b03b015b36', 'c227f51f-ac2c-4850-82ff-71b5e42c3c53', 5823, 'Flight completed', '2024-10-07 16:14:39');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('472f119e-202b-4396-86d3-9fa9e23d7f8b', 'b85f04f9-538a-4c65-a5c8-e0b03b015b36', '72e8cf81-04f4-427a-8f1f-c9fdc75a7b08', 3383, 'Flight completed', '2024-10-28 06:01:52');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('c58f2ad6-7471-42d7-9179-04ef0b4b642f', 'a122cbfc-0627-4c99-bdb9-f7277e98a8a9', 'b44f6d81-bc02-4098-a6c5-277ec45488e0', 4180, 'Flight completed', '2024-12-25 17:44:02');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('ad79ada3-2be7-498e-b62d-347cd93934bd', 'a122cbfc-0627-4c99-bdb9-f7277e98a8a9', 'b44f6d81-bc02-4098-a6c5-277ec45488e0', 1982, 'Flight completed', '2025-04-07 13:07:07');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('52b21935-f8a9-496e-a297-525ea7541f5e', 'a122cbfc-0627-4c99-bdb9-f7277e98a8a9', '1f57defe-8f38-4da5-b3d5-d6072b5b0e87', 6475, 'Flight completed', '2024-12-23 21:21:26');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('5789e6d3-c361-432e-8c6b-f5dc4bbf54e5', 'af49e5ce-3a5d-42a5-a43b-2ab6d9c9d6e6', 'c227f51f-ac2c-4850-82ff-71b5e42c3c53', 6177, 'Flight completed', '2025-01-30 13:22:04');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('504c518e-f65d-4b47-b7f2-5183866b2d66', 'af49e5ce-3a5d-42a5-a43b-2ab6d9c9d6e6', 'dd2930ed-1a75-4da3-8632-ff6da2262d6e', 4077, 'Flight completed', '2024-12-17 08:28:22');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('887e6dd8-1d68-4a5e-b5e8-d0422304e579', 'af49e5ce-3a5d-42a5-a43b-2ab6d9c9d6e6', 'dd2930ed-1a75-4da3-8632-ff6da2262d6e', 7535, 'Flight completed', '2025-03-25 18:33:56');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('9d949999-2a57-4606-8553-2ee0de5a6dce', '0f59fc27-49e2-4e1c-a264-2475c45c4909', '1994a787-4e43-4af8-8f10-68980c839b23', 7926, 'Flight completed', '2025-04-13 13:11:03');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('d04ee6e8-1979-43b1-a5b6-5a313c372e99', '0f59fc27-49e2-4e1c-a264-2475c45c4909', '1f57defe-8f38-4da5-b3d5-d6072b5b0e87', 3749, 'Flight completed', '2025-02-22 14:57:47');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('324df0e1-3d08-4a28-8496-c603f0f67fae', '0f59fc27-49e2-4e1c-a264-2475c45c4909', '734b351a-90fb-4a82-915f-637eec4b29d6', 9763, 'Flight completed', '2025-02-07 05:08:18');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('4dd14b82-6ba1-4725-9ffa-42f9526a8b26', 'dbdcd22e-79a6-42dc-b9f1-6a92ae7f350e', '1f57defe-8f38-4da5-b3d5-d6072b5b0e87', 1327, 'Flight completed', '2025-02-27 04:23:30');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('53c0e461-1483-48bc-9ec8-1a9dd106e73e', 'dbdcd22e-79a6-42dc-b9f1-6a92ae7f350e', '5536a34d-d1cc-425b-a457-20ee057217df', 5322, 'Flight completed', '2024-11-16 01:31:32');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('d3f97329-772a-499e-bb8f-27dc52f5bcd7', 'dbdcd22e-79a6-42dc-b9f1-6a92ae7f350e', '734b351a-90fb-4a82-915f-637eec4b29d6', 8779, 'Flight completed', '2025-02-23 09:56:18');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('3e062801-cdde-44ba-83e4-653618020edb', '4be7c2cb-3485-41f4-b8e6-41f3db041ed5', '431bdcec-5013-419a-86af-2880208e6c50', 8422, 'Flight completed', '2025-04-21 10:20:01');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('65473d3a-ab0a-4e16-90c3-b0200f488033', '4be7c2cb-3485-41f4-b8e6-41f3db041ed5', 'c227f51f-ac2c-4850-82ff-71b5e42c3c53', 3768, 'Flight completed', '2025-03-20 02:41:21');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('9770d08d-134e-422e-9cf4-80680d4cac55', '4be7c2cb-3485-41f4-b8e6-41f3db041ed5', '431bdcec-5013-419a-86af-2880208e6c50', 3065, 'Flight completed', '2025-02-10 21:17:24');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('b391062e-60f4-449c-b254-e0dfa046ca6c', 'f2cdabde-65b1-4a4c-b964-4e70e3cd4ed5', '1994a787-4e43-4af8-8f10-68980c839b23', 2594, 'Flight completed', '2024-12-31 23:17:17');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('69a0b077-d61e-40dc-b80a-8549b81d38c2', 'f2cdabde-65b1-4a4c-b964-4e70e3cd4ed5', 'd455870a-8b77-4a70-818d-c0639913beea', 8535, 'Flight completed', '2024-11-25 02:42:47');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('09edf565-09f1-487f-8cb0-a0e2e00b2e13', 'f2cdabde-65b1-4a4c-b964-4e70e3cd4ed5', '1e4eaf0f-5d68-4902-aa80-5929e5794eef', 7420, 'Flight completed', '2025-01-18 21:39:06');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('c8fddc97-cfb9-48e1-86c8-a74880e25456', 'e0f06f5f-0f44-4c6f-8c1a-20d778ebd4df', 'd455870a-8b77-4a70-818d-c0639913beea', 7271, 'Flight completed', '2025-02-17 07:26:02');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('895f7042-c805-47cc-af2f-0bd31958eed8', 'e0f06f5f-0f44-4c6f-8c1a-20d778ebd4df', '431bdcec-5013-419a-86af-2880208e6c50', 3498, 'Flight completed', '2025-02-20 08:42:49');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('c716d9f0-90f4-4c80-9e0a-d2883cf4bb74', 'e0f06f5f-0f44-4c6f-8c1a-20d778ebd4df', '5536a34d-d1cc-425b-a457-20ee057217df', 2340, 'Flight completed', '2024-11-10 18:32:46');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('ec2147e9-e2fe-4a58-8ae0-5ca72bc0a77a', '60bcbb15-8a50-42db-bdb0-071933dd5f79', '1e4eaf0f-5d68-4902-aa80-5929e5794eef', 3855, 'Flight completed', '2024-12-12 22:07:10');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('c7bfdc98-be9f-42ee-8da2-434ab402e347', '60bcbb15-8a50-42db-bdb0-071933dd5f79', '38828dab-a3ad-4dfc-95f9-fc34ec554cb6', 1060, 'Flight completed', '2024-11-13 22:26:42');
INSERT INTO miles_transactions (transaction_id, user_id, flight_id, miles_change, reason, transaction_time) VALUES ('caf9fe5e-602d-4a18-9ed8-baec5293a92e', '60bcbb15-8a50-42db-bdb0-071933dd5f79', '299abf1b-c3df-4aae-a25b-2b9544a41096', 5860, 'Flight completed', '2024-12-27 09:21:51');