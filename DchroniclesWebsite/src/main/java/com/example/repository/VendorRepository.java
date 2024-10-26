package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
