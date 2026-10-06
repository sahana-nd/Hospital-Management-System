# MediNova - Full-Stack Hospital Management System

A secure full-stack **Hospital Management System** engineered using **Java**, **Spring Boot**, **JDBC (`JdbcTemplate`)**, **MySQL**, **HTML5**, **CSS3**, and **JavaScript**.

---

## 🌟 Key Features & Deliverables

- **RESTful API Endpoints**:
  - `POST /api/auth/login` & `POST /api/auth/register` (Role-based Authentication)
  - `GET /api/doctors` & `POST /api/doctors` (Specialist Roster & Department Filtering)
  - `GET /api/patients` & `DELETE /api/patients/{id}` (Patient Record Management)
  - `GET /api/appointments`, `POST /api/appointments`, `DELETE /api/appointments/{id}` (Appointment Scheduling)
- **Role-Based Access Control**: Configured via Spring Security for `ROLE_ADMIN`, `ROLE_DOCTOR`, and `ROLE_PATIENT`.
- **Database Connectivity via JDBC**: Developed using Spring `JdbcTemplate` for high-performance direct SQL execution with MySQL / H2 database connectivity.
- **Modern Responsive Web UI**: Glassmorphic UI dashboard, dark/light theme switching, interactive appointment slot picker, live stats counters, and doctor directory.

---

## 🏗️ Project Architecture & Structure

```
Hospital Management System/
├── pom.xml                                 # Maven dependencies (Spring Boot, JDBC, Security, MySQL)
├── schema.sql                              # MySQL DDL Schema script
├── data.sql                                # Initial DML Seed Data
├── README.md
└── src/
    └── main/
        ├── java/com/hospital/management/
        │   ├── HospitalManagementApplication.java  # Main Boot Launcher
        │   ├── config/                     # SecurityConfig & CORS configuration
        │   ├── controller/                 # REST Controllers (Auth, Doctor, Patient, Appointment)
        │   ├── dao/                        # JDBC Data Access Objects using JdbcTemplate
        │   ├── dto/                        # Request & Response DTO wrappers
        │   ├── model/                      # Entities (User, Doctor, Patient, Appointment)
        │   └── service/                    # Business Logic Services
        └── resources/
            ├── application.properties      # DB Connection & Server Ports
            ├── schema.sql                  # Auto-executed DDL
            ├── data.sql                    # Auto-executed DML
            └── static/                     # HTML, CSS, JS Frontend Assets
```

---

## 🚀 How to Run the Application

### 1. Build and Run via Maven
```bash
mvn spring-boot:run
```

### 2. Accessing the Application
- **Web Interface**: [http://localhost:8080](http://localhost:8080)
- **H2 In-Memory DB Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  - **JDBC URL**: `jdbc:h2:mem:hospital_db`
  - **User**: `sa`
  - **Password**: *(leave blank)*

---

## 🗄️ MySQL Database Setup (Optional)
To switch from in-memory H2 to a live local MySQL server:
1. Open MySQL Workbench and create a schema named `hospital_db`:
   ```sql
   CREATE DATABASE hospital_db;
   ```
2. Uncomment the MySQL configuration lines in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

---

## 🧪 Postman REST API Testing Guide

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Register new user account |
| `POST` | `/api/auth/login` | Authenticate user & retrieve session |
| `GET` | `/api/doctors` | Get all doctors list |
| `GET` | `/api/doctors?specialization=Cardiology` | Filter doctors by department |
| `POST` | `/api/doctors` | Add a new doctor record |
| `GET` | `/api/patients` | Retrieve patient registry |
| `GET` | `/api/appointments` | Get all scheduled appointments |
| `POST` | `/api/appointments` | Book a new patient appointment |
| `DELETE` | `/api/appointments/{id}` | Cancel/delete an appointment |
