package com.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ApplyNow {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private String positionAppliedFor;
    private int yearsOfExperience;
    private String highestLevelOfEducation;
    private String courseName;
    
    @Column(length = 5000)
    private String coverLetter; 
    private String resume;       
    
    // Constructors, getters, and setters
    
    public ApplyNow() {
        super();
    }

    public ApplyNow(Long id, String fullName, String email, String phoneNumber, String positionAppliedFor,
                    int yearsOfExperience, String highestLevelOfEducation, String courseName,
                    String coverLetter, String resume) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.positionAppliedFor = positionAppliedFor;
        this.yearsOfExperience = yearsOfExperience;
        this.highestLevelOfEducation = highestLevelOfEducation;
        this.courseName = courseName;
        this.coverLetter = coverLetter;
        this.resume = resume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPositionAppliedFor() {
        return positionAppliedFor;
    }

    public void setPositionAppliedFor(String positionAppliedFor) {
        this.positionAppliedFor = positionAppliedFor;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getHighestLevelOfEducation() {
        return highestLevelOfEducation;
    }

    public void setHighestLevelOfEducation(String highestLevelOfEducation) {
        this.highestLevelOfEducation = highestLevelOfEducation;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Override
    public String toString() {
        return "ApplyNow [id=" + id + ", fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber
                + ", positionAppliedFor=" + positionAppliedFor + ", yearsOfExperience=" + yearsOfExperience
                + ", highestLevelOfEducation=" + highestLevelOfEducation + ", courseName=" + courseName
                + ", coverLetter=" + coverLetter + ", resume=" + resume + "]";
    }
}
