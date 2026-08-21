package com.cgorin.jobtracker.repository;

import com.cgorin.jobtracker.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> { 
	boolean existsByNameIgnoreCase(String name);
}
