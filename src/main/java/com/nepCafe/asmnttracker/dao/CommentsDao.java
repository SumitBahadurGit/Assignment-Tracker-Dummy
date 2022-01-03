package com.nepCafe.asmnttracker.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nepCafe.asmnttracker.dto.CommentsDTO;
import com.nepCafe.asmnttracker.repo.CommentsRepo;

@Service
public class CommentsDao {

	private CommentsRepo commentsRepo;
	
	@Autowired
	public CommentsDao(CommentsRepo commentsRepo) {
		this.commentsRepo = commentsRepo;
	}
	
	public CommentsDTO saveAndFlush(CommentsDTO entity) {
		return commentsRepo.saveAndFlush(entity);
	}
	
	public CommentsDTO save(CommentsDTO entity) {
		return commentsRepo.save(entity);
	}
	
	public List<CommentsDTO> save(List<CommentsDTO> entities) {
		return commentsRepo.saveAll(entities);
	}
	
	public CommentsDTO find(Long id) {

		Optional<CommentsDTO> optional = commentsRepo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}
	
	public List<CommentsDTO> findAll(Long atId) {		
		return commentsRepo.findAllByAtId(atId);
	}
	
}
