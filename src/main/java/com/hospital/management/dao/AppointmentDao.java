package com.hospital.management.dao;

import com.hospital.management.model.Appointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings("unused")
public class AppointmentDao {
    private final JdbcTemplate jdbcTemplate;

    public AppointmentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Appointment> appointmentRowMapper = (rs, rowNum) -> new Appointment(
        rs.getLong("id"),
        rs.getString("appointment_code"),
        rs.getString("patient_name"),
        rs.getString("patient_phone"),
        rs.getInt("patient_age"),
        rs.getString("doctor_name"),
        rs.getString("specialization"),
        rs.getString("appointment_date"),
        rs.getString("appointment_time"),
        rs.getString("status"),
        rs.getString("consultation_type"),
        rs.getTimestamp("created_at")
    );

    public List<Appointment> findAll() {
        String sql = "SELECT * FROM appointments ORDER BY id DESC";
        return jdbcTemplate.query(sql, appointmentRowMapper);
    }

    public Optional<Appointment> findById(Long id) {
        String sql = "SELECT * FROM appointments WHERE id = ?";
        List<Appointment> appointments = jdbcTemplate.query(sql, appointmentRowMapper, id);
        return appointments.stream().findFirst();
    }

    public Optional<Appointment> findByCode(String code) {
        String sql = "SELECT * FROM appointments WHERE appointment_code = ?";
        List<Appointment> appointments = jdbcTemplate.query(sql, appointmentRowMapper, code);
        return appointments.stream().findFirst();
    }

    public int save(Appointment apt) {
        String sql = "INSERT INTO appointments (appointment_code, patient_name, patient_phone, patient_age, doctor_name, specialization, appointment_date, appointment_time, status, consultation_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            apt.getAppointmentCode(),
            apt.getPatientName(),
            apt.getPatientPhone(),
            apt.getPatientAge() != null ? apt.getPatientAge() : 30,
            apt.getDoctorName(),
            apt.getSpecialization(),
            apt.getAppointmentDate(),
            apt.getAppointmentTime(),
            apt.getStatus() != null ? apt.getStatus() : "Confirmed",
            apt.getConsultationType() != null ? apt.getConsultationType() : "In-Person"
        );
    }

    public int updateStatus(Long id, String status) {
        String sql = "UPDATE appointments SET status = ? WHERE id = ?";
        return jdbcTemplate.update(sql, status, id);
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM appointments WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
