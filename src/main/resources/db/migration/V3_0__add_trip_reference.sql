ALTER TABLE bookings ADD COLUMN trip_reference_id UUID;

ALTER TABLE payments ADD COLUMN trip_reference_id UUID;

ALTER TABLE payments
    ADD CONSTRAINT chk_payment_target CHECK (
        booking_id IS NOT NULL OR trip_reference_id IS NOT NULL
    );
