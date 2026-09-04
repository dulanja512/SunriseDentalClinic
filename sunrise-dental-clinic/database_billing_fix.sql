-- Sunrise Dental Clinic - Billing correction
-- Run this once on an existing database that already contains bills.
-- Treatment base_cost is the authoritative charge; the previous hard-coded
-- Rs. 1,500 consultation fee is removed to prevent double charging.

UPDATE bills b
JOIN appointments a ON a.appointment_id = b.appointment_id
JOIN treatments t ON t.treatment_id = a.treatment_id
SET b.consultation_fee = 0,
    b.treatment_cost = t.base_cost,
    b.total_amount = GREATEST(t.base_cost - COALESCE(b.discount, 0), 0),
    b.pricing_strategy = 'Standard Treatment Pricing';
