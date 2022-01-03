package com.nepCafe.asmnttracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nepCafe.asmnttracker.dto.CommentsDTO;

@Repository
public interface CommentsRepo extends JpaRepository<CommentsDTO, Long>{
	
	@Query("SELECT U FROM COMMENTS U WHERE U.atId = ?1")
	List<CommentsDTO> findAllByAtId(Long atId);

}
