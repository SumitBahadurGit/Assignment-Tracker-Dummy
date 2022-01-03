package com.nepCafe.asmnttracker.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nepCafe.asmnttracker.dto.LogInDto;

@Repository
public interface LoginRepo extends JpaRepository<LogInDto, Long>{

	public Optional<LogInDto> findByUserNameAndPw(String userName, String pw);

	public Optional<LogInDto> findByUserName(String userName);
}
