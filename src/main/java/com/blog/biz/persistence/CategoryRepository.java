package com.blog.biz.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.biz.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	//블로그별 카테고리 조회
	List<Category> findByBlogIdOrderByCategoryIdDesc(int blogId);
	
	//블로그 삭제시 카테고리 목록 삭제
	void deleteByBlogId(int blogId);
}
