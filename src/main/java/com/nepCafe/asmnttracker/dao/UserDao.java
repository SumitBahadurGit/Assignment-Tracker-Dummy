package com.nepCafe.asmnttracker.dao;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.nepCafe.asmnttracker.dto.UserDto;
import com.nepCafe.asmnttracker.repo.UserRepo;

@Component
public class UserDao {

	private EntityManager em;
	private UserRepo userRepo;

	@Autowired
	public UserDao(UserRepo userRepo, EntityManager em) {
		this.userRepo = userRepo;
		this.em = em;
	}

	public UserDto save(UserDto entity) {
		entity.setLastUpdated(Timestamp.from(Instant.now()));
		return userRepo.save(entity);
	}

	public List<UserDto> save(List<UserDto> entities) {
		entities.forEach(e -> {
			e.setLastUpdated(Timestamp.from(Instant.now()));
		});
		return userRepo.saveAll(entities);
	}

	public UserDto find(Long id) {
		Optional<UserDto> optional = userRepo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	public List<UserDto> findAll() {
		return userRepo.findAll();
	}
	
	public List<UserDto> findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	public List<UserDto> searchUsers(String searchTerm) {
		
		List<UserDto> response = new ArrayList<>();
		String sql = "SELECT u.id, u.firstName, u.lastName FROM USER_ u "
				+ " WHERE UPPER(u.firstName) LIKE '%" +searchTerm.toUpperCase()+ "%'"
				+ " OR UPPER(u.lastName) LIKE '%" + searchTerm.toUpperCase()+ "%' "
				+ " OR u.id LIKE '%" +searchTerm+ "%'";
		TypedQuery<Object[]> q = em.createQuery(sql, Object[].class);
		List<Object[]> result = q.getResultList();

		if(result != null) {
			for (Object[] o : result) {
				Long id = (Long)o[0];
				String firstName = (String)o[1];
				String lastName = (String)o[2];	
				response.add(new UserDto(id, firstName, lastName));
			}
		}
		return response;
	}

	public Page<UserDto> findUsers(String role, String empStatus, int page, int size) {

		Pageable pageable = PageRequest.of(page, size);

		if (empStatus != null && role != null) {
			return userRepo.findByRoleAndEmpStatus(role, empStatus, pageable);
		} else if (empStatus != null) {
			return userRepo.findByEmpStatus(empStatus, pageable);
		} else if (role != null) {
			return userRepo.findByRole(role, pageable);
		} else {
			return userRepo.findAll(pageable);
		}

	}

	public List<Map<Object, Object>> getActiveEmpStatsByStatus() {
		// TODO Auto-generated method stub
		return userRepo.findEmpStatsByStatus();
	}

}
