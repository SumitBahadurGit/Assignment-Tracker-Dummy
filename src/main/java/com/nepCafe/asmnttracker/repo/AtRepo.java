package com.nepCafe.asmnttracker.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nepCafe.asmnttracker.dto.ATResponseDTO;

@Repository
public interface AtRepo extends JpaRepository<ATResponseDTO, Long> {

	@Query("SELECT  a.status, COUNT(a.atIt) FROM ASSIGNMENT a GROUP BY  a.status")
	public List<Object[]> getAllCount();
	
	@Query("SELECT  a.status, COUNT(a.atIt) FROM ASSIGNMENT a GROUP BY  a.status")
	public List<Object[]> getAllCountByStatus(String status);
	
	public Page<ATResponseDTO> findByStatus(String status, Pageable pageable);
	 
}
