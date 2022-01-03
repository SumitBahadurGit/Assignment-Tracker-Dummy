package com.nepCafe.asmnttracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nepCafe.asmnttracker.dto.AttachmentsDto;

@Repository
public interface AttachmentsRepo extends JpaRepository<AttachmentsDto, Long>{

	@Query("SELECT U FROM Attachments U WHERE U.atId = ?1")
	List<AttachmentsDto> findAllByAtId(Long atId);

}
