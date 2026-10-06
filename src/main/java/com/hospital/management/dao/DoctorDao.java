package com.hospital.management.dao;

import com.hospital.management.model.Doctor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings("unused")
public class DoctorDao {
    private final JdbcTemplate jdbcTemplate;

    public DoctorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Doctor> doctorRowMapper = (rs, rowNum) -> new Doctor(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getString("specialization"),
        rs.getString("experience"),
        rs.getString("rating"),
        rs.getString("status"),
        rs.getString("fee"),
        rs.getString("days"),
        rs.getString("avatar")
    );

    public List<Doctor> findAll() {
        String sql = "SELECT * FROM doctors ORDER BY id ASC";
        return jdbcTemplate.query(sql, doctorRowMapper);
    }

    public Optional<Doctor> findById(Long id) {
        String sql = "SELECT * FROM doctors WHERE id = ?";
        List<Doctor> doctors = jdbcTemplate.query(sql, doctorRowMapper, id);
        return doctors.stream().findFirst();
    }

    public List<Doctor> findBySpecialization(String specialization) {
        String sql = "SELECT * FROM doctors WHERE LOWER(specialization) LIKE LOWER(?)";
        return jdbcTemplate.query(sql, doctorRowMapper, "%" + specialization + "%");
    }

    public int save(Doctor doctor) {
        String sql = "INSERT INTO doctors (name, specialization, experience, rating, status, fee, days, avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, 
            doctor.getName(), 
            doctor.getSpecialization(), 
            doctor.getExperience() != null ? doctor.getExperience() : "5 Yrs Exp", 
            doctor.getRating() != null ? doctor.getRating() : "5.0", 
            doctor.getStatus() != null ? doctor.getStatus() : "Available", 
            doctor.getFee() != null ? doctor.getFee() : "$150", 
            doctor.getDays() != null ? doctor.getDays() : "Mon - Fri", 
            doctor.getAvatar() != null ? doctor.getAvatar() : "DR"
        );
    }

    public int updateStatus(Long id, String status) {
        String sql = "UPDATE doctors SET status = ? WHERE id = ?";
        return jdbcTemplate.update(sql, status, id);
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM doctors WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
