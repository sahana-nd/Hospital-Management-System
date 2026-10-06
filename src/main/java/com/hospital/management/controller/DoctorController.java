package com.hospital.management.controller;

import com.hospital.management.dto.ApiResponse;
import com.hospital.management.model.Doctor;
import com.hospital.management.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Doctor>>> getAllDoctors(
            @RequestParam(name = "specialization", required = false) String specialization) {
        List<Doctor> doctors;
        if (specialization != null && !specialization.isEmpty()) {
            doctors = doctorService.getDoctorsBySpecialization(specialization);
        } else {
            doctors = doctorService.getAllDoctors();
        }
        return ResponseEntity.ok(ApiResponse.ok("Doctors retrieved successfully", doctors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Doctor>> getDoctorById(@PathVariable("id") Long id) {
        try {
            Doctor doctor = doctorService.getDoctorById(id);
            return ResponseEntity.ok(ApiResponse.ok("Doctor found", doctor));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Doctor>> addDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor saved = doctorService.addDoctor(doctor);
            return ResponseEntity.ok(ApiResponse.ok("Doctor added successfully", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<String>> updateDoctorStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        boolean updated = doctorService.updateDoctorStatus(id, status);
        if (updated) {
            return ResponseEntity.ok(ApiResponse.ok("Doctor status updated", status));
        }
        return ResponseEntity.badRequest().body(ApiResponse.error("Failed to update status"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDoctor(@PathVariable("id") Long id) {
        boolean deleted = doctorService.deleteDoctor(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.ok("Doctor record deleted", null));
        }
        return ResponseEntity.badRequest().body(ApiResponse.error("Failed to delete doctor"));
    }
}
