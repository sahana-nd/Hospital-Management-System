-- Hospital Management System Database Schema (MySQL Compatible)

DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS doctors;
DROP TABLE IF EXISTS users;

-- Users Table for Authentication & Role-Based Access Control
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL, -- ROLE_ADMIN, ROLE_DOCTOR, ROLE_PATIENT
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Doctors Table
CREATE TABLE doctors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    experience VARCHAR(50) NOT NULL,
    rating VARCHAR(20) DEFAULT '4.8',
    status VARCHAR(50) DEFAULT 'Available',
    fee VARCHAR(50) DEFAULT '$150',
    days VARCHAR(100) DEFAULT 'Mon - Fri',
    avatar VARCHAR(10) DEFAULT 'DR'
);

-- Patients Table
CREATE TABLE patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150),
    phone VARCHAR(50) NOT NULL,
    age INT,
    gender VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Appointments Table
CREATE TABLE appointments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    appointment_code VARCHAR(50) NOT NULL UNIQUE,
    patient_name VARCHAR(150) NOT NULL,
    patient_phone VARCHAR(50),
    patient_age INT DEFAULT 30,
    doctor_name VARCHAR(150) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    appointment_date VARCHAR(50) NOT NULL,
    appointment_time VARCHAR(50) NOT NULL,
    status VARCHAR(50) DEFAULT 'Confirmed',
    consultation_type VARCHAR(50) DEFAULT 'In-Person',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
