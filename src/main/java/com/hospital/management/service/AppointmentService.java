package com.hospital.management.service;

import com.hospital.management.dao.AppointmentDao;
import com.hospital.management.model.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AppointmentService {
    private final AppointmentDao appointmentDao;

    public AppointmentService(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDao.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    public Appointment createAppointment(Appointment appointment) {
        if (appointment.getAppointmentCode() == null || appointment.getAppointmentCode().isEmpty()) {
            appointment.setAppointmentCode("APT-" + (100 + new Random().nextInt(900)));
        }
        if (appointment.getStatus() == null) {
            appointment.setStatus("Confirmed");
        }
        appointmentDao.save(appointment);
        return appointmentDao.findByCode(appointment.getAppointmentCode()).orElse(appointment);
    }

    public boolean updateStatus(Long id, String status) {
        return appointmentDao.updateStatus(id, status) > 0;
    }

    public boolean deleteAppointment(Long id) {
        return appointmentDao.deleteById(id) > 0;
    }
}
