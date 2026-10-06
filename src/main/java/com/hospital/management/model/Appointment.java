package com.hospital.management.model;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String appointmentCode;
    private String patientName;
    private String patientPhone;
    private Integer patientAge;
    private String doctorName;
    private String specialization;
    private String appointmentDate;
    private String appointmentTime;
    private String status;
    private String consultationType;
    private Timestamp createdAt;

    public Appointment() {}

    public Appointment(Long id, String appointmentCode, String patientName, String patientPhone, Integer patientAge, 
                       String doctorName, String specialization, String appointmentDate, String appointmentTime, 
                       String status, String consultationType, Timestamp createdAt) {
        this.id = id;
        this.appointmentCode = appointmentCode;
        this.patientName = patientName;
        this.patientPhone = patientPhone;
        this.patientAge = patientAge;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.consultationType = consultationType;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAppointmentCode() { return appointmentCode; }
    public void setAppointmentCode(String appointmentCode) { this.appointmentCode = appointmentCode; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getPatientPhone() { return patientPhone; }
    public void setPatientPhone(String patientPhone) { this.patientPhone = patientPhone; }

    public Integer getPatientAge() { return patientAge; }
    public void setPatientAge(Integer patientAge) { this.patientAge = patientAge; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getConsultationType() { return consultationType; }
    public void setConsultationType(String consultationType) { this.consultationType = consultationType; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
