package com.nepCafe.asmnttracker.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nepCafe.asmnttracker.dto.UserDto;

@Repository
public interface UserRepo extends JpaRepository<UserDto, Long>{

	@Query(value = "SELECT new com.nepCafe.asmnttracker.dto.UserDto(u.ID, u.FIRST_NAME, u.LAST_NAME) FROM USER_ u "
			+ "WHERE UPPER(u.FIRST_NAME) LIKE ?1"
			+ " OR    UPPER(u.LAST_NAME) LIKE ?1"
			+ " OR  u.ID LIKE ?1", nativeQuery = true)
	public List<UserDto> searchUsers(String searchTerm);
	
	public Page<UserDto> findByRole(String role, Pageable pageable);
	
	public Page<UserDto> findByEmpStatus(String status, Pageable pageable);
	
	public Page<UserDto> findByRoleAndEmpStatus(String role, String status, Pageable pageable);

	public List<UserDto> findByEmail(String email);
	
	@Query(value = "select u.immigration_status as STATUS, count(*) as COUNT from user_ u\r\n"
			+ "join employment e on e.user_id =  u.id\r\n"
			+ "where u.role_type = 'CONSULTANT'\r\n"
			+ "and e.status = 'ACTIVE'\r\n"
			+ "group by u.immigration_status", nativeQuery = true)
	List<Map<Object, Object>> findEmpStatsByStatus();
	
}
