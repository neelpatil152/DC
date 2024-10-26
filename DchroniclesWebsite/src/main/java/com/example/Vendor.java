//package com.example.models;
//
//import java.util.Arrays;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Lob;
//
//@Entity
//public class Vendor {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String companyName;
//    private String companyWebsite;
//    private String phoneNumber;
//    private String country;
//    private String companyAddress;
//    private String district;
//    private String postalCode;
//    private String companyId;
//
//    @Lob
//    private byte[] document; // for file upload
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getCompanyName() {
//		return companyName;
//	}
//
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}
//
//	public String getCompanyWebsite() {
//		return companyWebsite;
//	}
//
//	public void setCompanyWebsite(String companyWebsite) {
//		this.companyWebsite = companyWebsite;
//	}
//
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	public String getCountry() {
//		return country;
//	}
//
//	public void setCountry(String country) {
//		this.country = country;
//	}
//
//	public String getCompanyAddress() {
//		return companyAddress;
//	}
//
//	public void setCompanyAddress(String companyAddress) {
//		this.companyAddress = companyAddress;
//	}
//
//	public String getDistrict() {
//		return district;
//	}
//
//	public void setDistrict(String district) {
//		this.district = district;
//	}
//
//	public String getPostalCode() {
//		return postalCode;
//	}
//
//	public void setPostalCode(String postalCode) {
//		this.postalCode = postalCode;
//	}
//
//	public String getCompanyId() {
//		return companyId;
//	}
//
//	public void setCompanyId(String companyId) {
//		this.companyId = companyId;
//	}
//
//	public byte[] getDocument() {
//		return document;
//	}
//
//	public void setDocument(byte[] document) {
//		this.document = document;
//	}
//
//	public Vendor(Long id, String companyName, String companyWebsite, String phoneNumber, String country,
//			String companyAddress, String district, String postalCode, String companyId, byte[] document) {
//		super();
//		this.id = id;
//		this.companyName = companyName;
//		this.companyWebsite = companyWebsite;
//		this.phoneNumber = phoneNumber;
//		this.country = country;
//		this.companyAddress = companyAddress;
//		this.district = district;
//		this.postalCode = postalCode;
//		this.companyId = companyId;
//		this.document = document;
//	}
//
//	public Vendor() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public String toString() {
//		return "Vendor [id=" + id + ", companyName=" + companyName + ", companyWebsite=" + companyWebsite
//				+ ", phoneNumber=" + phoneNumber + ", country=" + country + ", companyAddress=" + companyAddress
//				+ ", district=" + district + ", postalCode=" + postalCode + ", companyId=" + companyId + ", document="
//				+ Arrays.toString(document) + "]";
//	}
//
//    
//}
package com.example;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private String companyName;

    private String companyWebsite;

    private String phoneNumber;

    private String country;

    private String address;

    private String district;

    private String postalCode;

    private String companyId;

    private String uploadDocument;  // Adjusted to match the database column name

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getUploadDocument() {
		return uploadDocument;
	}

	public void setUploadDocument(String uploadDocument) {
		this.uploadDocument = uploadDocument;
	}

	   
}

