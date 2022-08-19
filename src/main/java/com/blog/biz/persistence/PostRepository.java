package com.blog.biz.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.biz.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	//카테고리별 포스트 삭제
	void deleteByCategoryId(int CategoryId);
	
	//블로그 삭제시 포스트 목록 삭제
	void deleteByBlogId(int blogId);
	
	//블로그 내 모든 포스트 조회
	List<Post> findByBlogIdOrderByPostIdDesc(int blogId);
	
	//블로그 내 카테고리별 포스트 조회
	List<Post> findByCategoryIdOrderByPostIdDesc(int categoryId);
}
