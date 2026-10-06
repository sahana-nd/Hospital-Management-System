package com.hospital.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HospitalManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalManagementApplication.class, args);
        System.out.println("\n========================================================");
        System.out.println("  MediNova Hospital Management System Server Running!   ");
        System.out.println("  Spring Boot + JDBC + MySQL/H2 DB                      ");
        System.out.println("  Access Web Application: http://localhost:8080         ");
        System.out.println("  H2 Console:             http://localhost:8080/h2-console");
        System.out.println("========================================================\n");
    }
}
