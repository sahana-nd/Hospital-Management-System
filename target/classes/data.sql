-- Initial Seed Data for Hospital Management System

-- Seed Users (Passwords hashed or plain for JDBC Auth Demo)
INSERT INTO users (username, email, password, role) VALUES 
('admin', 'admin@medinova.com', '$2a$10$e8W/2sR9cM.JtEwS.v7f1.x7.zY5j4F5D6C7B8A9O0P1Q2R3S4T5U', 'ROLE_ADMIN'),
('dr_sharma', 'sharma@medinova.com', '$2a$10$e8W/2sR9cM.JtEwS.v7f1.x7.zY5j4F5D6C7B8A9O0P1Q2R3S4T5U', 'ROLE_DOCTOR'),
('sarah_j', 'sarah@example.com', '$2a$10$e8W/2sR9cM.JtEwS.v7f1.x7.zY5j4F5D6C7B8A9O0P1Q2R3S4T5U', 'ROLE_PATIENT');

-- Seed Doctors Roster
INSERT INTO doctors (name, specialization, experience, rating, status, fee, days, avatar) VALUES 
('Dr. Ananya Sharma', 'Cardiology', '12 Yrs Exp', '4.9', 'Available', '$150', 'Mon - Fri', 'AS'),
('Dr. Rajesh Rao', 'Neurology', '15 Yrs Exp', '4.8', 'Available', '$180', 'Mon - Thu', 'RR'),
('Dr. Kumuda V.', 'Gynecology', '9 Yrs Exp', '4.9', 'On Leave', '$130', 'Tue - Sat', 'KV'),
('Dr. Laya Patel', 'Pediatrics', '10 Yrs Exp', '4.7', 'Available', '$120', 'Mon - Wed', 'LP'),
('Dr. Vikram Seth', 'Orthopedics', '14 Yrs Exp', '4.9', 'Available', '$160', 'Wed - Sun', 'VS'),
('Dr. Meera Nambiar', 'Dermatology', '8 Yrs Exp', '4.8', 'Available', '$140', 'Mon - Fri', 'MN');

-- Seed Patients
INSERT INTO patients (name, email, phone, age, gender) VALUES 
('Sarah Jenkins', 'sarah@example.com', '+1 555-0142', 32, 'Female'),
('Robert Chen', 'robert@example.com', '+1 555-0891', 45, 'Male'),
('Elena Rostova', 'elena@example.com', '+1 555-0312', 29, 'Female'),
('Marcus Vance', 'marcus@example.com', '+1 555-0943', 58, 'Male');

-- Seed Appointments
INSERT INTO appointments (appointment_code, patient_name, patient_phone, patient_age, doctor_name, specialization, appointment_date, appointment_time, status, consultation_type) VALUES 
('APT-101', 'Sarah Jenkins', '+1 555-0142', 32, 'Dr. Ananya Sharma', 'Cardiology', '2026-10-12', '10:00 AM', 'Confirmed', 'In-Person'),
('APT-102', 'Robert Chen', '+1 555-0891', 45, 'Dr. Rajesh Rao', 'Neurology', '2026-10-12', '11:30 AM', 'Pending', 'Telehealth'),
('APT-103', 'Elena Rostova', '+1 555-0312', 29, 'Dr. Laya Patel', 'Pediatrics', '2026-10-14', '02:00 PM', 'Confirmed', 'In-Person'),
('APT-104', 'Marcus Vance', '+1 555-0943', 58, 'Dr. Vikram Seth', 'Orthopedics', '2026-10-15', '04:15 PM', 'Confirmed', 'In-Person');
