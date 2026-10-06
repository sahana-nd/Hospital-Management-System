package com.hospital.management.controller;

import com.hospital.management.dto.ApiResponse;
import com.hospital.management.model.Patient;
import com.hospital.management.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Patient>>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(ApiResponse.ok("Patients retrieved successfully", patients));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> getPatientById(@PathVariable("id") Long id) {
        try {
            Patient patient = patientService.getPatientById(id);
            return ResponseEntity.ok(ApiResponse.ok("Patient found", patient));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Patient>> addPatient(@RequestBody Patient patient) {
        try {
            Patient saved = patientService.addPatient(patient);
            return ResponseEntity.ok(ApiResponse.ok("Patient added successfully", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePatient(@PathVariable("id") Long id) {
        boolean deleted = patientService.deletePatient(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.ok("Patient record deleted", null));
        }
        return ResponseEntity.badRequest().body(ApiResponse.error("Failed to delete patient"));
    }
}
