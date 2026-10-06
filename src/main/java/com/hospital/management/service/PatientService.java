package com.hospital.management.service;

import com.hospital.management.dao.PatientDao;
import com.hospital.management.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientDao patientDao;

    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public List<Patient> getAllPatients() {
        return patientDao.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

    public Patient addPatient(Patient patient) {
        patientDao.save(patient);
        return patient;
    }

    public boolean deletePatient(Long id) {
        return patientDao.deleteById(id) > 0;
    }
}
