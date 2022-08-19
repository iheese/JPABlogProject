package com.blog.biz.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.biz.domain.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer>{
	
	//TITLE일때 검색 조회
	List<Blog> findByTitleContainsIgnoreCaseOrderByBlogIdDesc(String keyword);
	
	//TAG일때 검색 조회
	List<Blog> findByTagContainsIgnoreCaseOrderByBlogIdDesc(String keyword);
}
