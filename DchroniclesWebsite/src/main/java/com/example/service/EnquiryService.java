package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Enquiry;
import com.example.repository.EnquiryRepository;

@Service
public class EnquiryService {
	
	@Autowired
	private EnquiryRepository repository;
	
	public Enquiry saveEnquiry(Enquiry enquiry)
	{
		return  repository.save(enquiry);
		
	}

}
