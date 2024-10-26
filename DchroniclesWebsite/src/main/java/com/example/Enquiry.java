package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Enquiry {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private String course;
    private String highestLevelOfEducation;
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
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getHighestLevelOfEducation() {
		return highestLevelOfEducation;
	}
	public void setHighestLevelOfEducation(String highestLevelOfEducation) {
		this.highestLevelOfEducation = highestLevelOfEducation;
	}
	public Enquiry(Long id, String fullName, String email, String phoneNumber, String course,
			String highestLevelOfEducation) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.course = course;
		this.highestLevelOfEducation = highestLevelOfEducation;
	}
	public Enquiry() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Enquiry [id=" + id + ", fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", course=" + course + ", highestLevelOfEducation=" + highestLevelOfEducation + "]";
	}

    
}
