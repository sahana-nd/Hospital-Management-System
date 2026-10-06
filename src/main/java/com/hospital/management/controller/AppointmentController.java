package com.hospital.management.controller;

import com.hospital.management.dto.ApiResponse;
import com.hospital.management.model.Appointment;
import com.hospital.management.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Appointment>>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(ApiResponse.ok("Appointments retrieved successfully", appointments));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Appointment>> getAppointmentById(@PathVariable("id") Long id) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id);
            return ResponseEntity.ok(ApiResponse.ok("Appointment found", appointment));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Appointment>> bookAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment created = appointmentService.createAppointment(appointment);
            return ResponseEntity.ok(ApiResponse.ok("Appointment booked successfully", created));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<String>> updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        boolean updated = appointmentService.updateStatus(id, status);
        if (updated) {
            return ResponseEntity.ok(ApiResponse.ok("Appointment status updated to " + status, status));
        }
        return ResponseEntity.badRequest().body(ApiResponse.error("Failed to update appointment status"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> cancelAppointment(@PathVariable("id") Long id) {
        boolean deleted = appointmentService.deleteAppointment(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.ok("Appointment cancelled and deleted", null));
        }
        return ResponseEntity.badRequest().body(ApiResponse.error("Failed to cancel appointment"));
    }
}
