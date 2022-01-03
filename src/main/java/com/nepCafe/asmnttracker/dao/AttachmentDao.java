package com.nepCafe.asmnttracker.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nepCafe.asmnttracker.dto.AttachmentsDto;
import com.nepCafe.asmnttracker.repo.AttachmentsRepo;

@Component
public class AttachmentDao {

	private AttachmentsRepo repo;
	
	@Autowired
	public AttachmentDao(AttachmentsRepo repo) {
		this.repo = repo;
	}
	
	public AttachmentsDto save(AttachmentsDto entity) {
		return repo.save(entity);
	}
	
	public List<AttachmentsDto> save(List<AttachmentsDto> entities) {
		return repo.saveAll(entities);
	}
	
	public AttachmentsDto find(Long id) {
		Optional<AttachmentsDto> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}
	
	public List<AttachmentsDto> findAll(Long atId) {		
		return repo.findAllByAtId(atId);
	}
}
