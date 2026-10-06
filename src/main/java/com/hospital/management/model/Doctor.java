package com.hospital.management.model;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String specialization;
    private String experience;
    private String rating;
    private String status;
    private String fee;
    private String days;
    private String avatar;

    public Doctor() {}

    public Doctor(Long id, String name, String specialization, String experience, String rating, String status, String fee, String days, String avatar) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
        this.rating = rating;
        this.status = status;
        this.fee = fee;
        this.days = days;
        this.avatar = avatar;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFee() { return fee; }
    public void setFee(String fee) { this.fee = fee; }

    public String getDays() { return days; }
    public void setDays(String days) { this.days = days; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
}
