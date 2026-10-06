package com.hospital.management.service;

import com.hospital.management.dao.DoctorDao;
import com.hospital.management.model.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorDao doctorDao;

    public DoctorService(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    public List<Doctor> getAllDoctors() {
        return doctorDao.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    public List<Doctor> getDoctorsBySpecialization(String spec) {
        return doctorDao.findBySpecialization(spec);
    }

    public Doctor addDoctor(Doctor doctor) {
        if (!doctor.getName().startsWith("Dr.")) {
            doctor.setName("Dr. " + doctor.getName());
        }
        doctorDao.save(doctor);
        return doctor;
    }

    public boolean updateDoctorStatus(Long id, String status) {
        return doctorDao.updateStatus(id, status) > 0;
    }

    public boolean deleteDoctor(Long id) {
        return doctorDao.deleteById(id) > 0;
    }
}
