package com.hospital.management.dao;

import com.hospital.management.model.Patient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings("unused")
public class PatientDao {
    private final JdbcTemplate jdbcTemplate;

    public PatientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Patient> patientRowMapper = (rs, rowNum) -> new Patient(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getString("email"),
        rs.getString("phone"),
        rs.getInt("age"),
        rs.getString("gender"),
        rs.getTimestamp("created_at")
    );

    public List<Patient> findAll() {
        String sql = "SELECT * FROM patients ORDER BY id DESC";
        return jdbcTemplate.query(sql, patientRowMapper);
    }

    public Optional<Patient> findById(Long id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        List<Patient> patients = jdbcTemplate.query(sql, patientRowMapper, id);
        return patients.stream().findFirst();
    }

    public int save(Patient patient) {
        String sql = "INSERT INTO patients (name, email, phone, age, gender) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, patient.getName(), patient.getEmail(), patient.getPhone(), patient.getAge(), patient.getGender());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM patients WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
