package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Enquiry;


public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {

}
