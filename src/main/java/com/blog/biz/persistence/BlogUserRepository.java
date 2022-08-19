package com.blog.biz.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.biz.domain.BlogUser;

public interface BlogUserRepository extends JpaRepository<BlogUser, Integer>{
	
	//유저 아이디로 유저 객체 조회
	Optional<BlogUser> findByUsername(String username);
}
