package com.blog.biz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.biz.domain.Blog;
import com.blog.biz.domain.Category;
import com.blog.biz.dto.BlogDto;
import com.blog.biz.persistence.BlogRepository;
import com.blog.biz.persistence.CategoryRepository;
import com.blog.biz.persistence.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BlogService {

	private final BlogRepository blogRepository;
	private final CategoryRepository categoryRepository;
	private final PostRepository postRepository;
	private final ModelMapper modelMapper;
	
	//BlogList를 BlogDtoList로 변환하는 함수
	private List<BlogDto> mapperToDtoList(List<Blog> blogList) {
		return blogList.stream().map(blog -> modelMapper.map(blog, BlogDto.class)).collect(Collectors.toList());
	}
	
	// 블로그 생성
	@Transactional
	public BlogDto insertBlog(BlogDto blogDto) {
		//블로그 객체 생성
		BlogDto saveBlogDto = BlogDto.builder()
							.blogId(blogDto.getBlogId())
							.status("운영")
							.tag("NO tags")
							.title(blogDto.getTitle())
							.build();
		
		//카테고리 객체 생성
		Category category = Category.builder()
				 		  .blogId(saveBlogDto.getBlogId())
						  .categoryName("미분류")
						  .description("기본으로 제공되는 카테고리입니다.")
						  .displayType("제목 + 내용")
						  .build();
		
		Blog saveBlog = modelMapper.map(saveBlogDto, Blog.class);
		
		blogRepository.save(saveBlog); //블로그 저장
		categoryRepository.save(category); //카테고리 저장
		return saveBlogDto;
	}
	
	//블로그 설정 수정
	@Transactional
	public void updateBlog(BlogDto blogDto) {
		Blog blog = modelMapper.map(blogDto, Blog.class);
		blogRepository.save(blog);
	}
	
	//블로그 목록 조회
	@Transactional(readOnly=true)
	public List<BlogDto> getBlogList() {
		List<Blog> blogList = blogRepository.findAll();
		return mapperToDtoList(blogList);
	}
	
	//블로그 상세 조회
	@Transactional(readOnly=true)
	public BlogDto getBlog(int blogId) {
		Blog blog=blogRepository.findById(blogId).orElseGet(Blog::new);
		BlogDto findBlog = modelMapper.map(blog, BlogDto.class); 
		return findBlog;
	}
	
	//블로그 검색 조회
	@Transactional(readOnly=true)
	public List<BlogDto> searchBlog(String searchCondition, String searchKeyword) {
		if(searchCondition.equals("TITLE")) {
			List<Blog> blogList = blogRepository.findByTitleContainsIgnoreCaseOrderByBlogIdDesc(searchKeyword);
			return mapperToDtoList(blogList);
		}
		if(searchCondition.equals("TAG")) {
			List<Blog> blogList = blogRepository.findByTagContainsIgnoreCaseOrderByBlogIdDesc(searchKeyword);
			return mapperToDtoList(blogList);
		}
		List<Blog> blogList = blogRepository.findAll();
		return mapperToDtoList(blogList);
	}
	
	//블로그 삭제 요청
	@Transactional
	public void removeApplyBlog(int blogId) {
		Blog blog = blogRepository.findById(blogId).orElseGet(Blog::new);
		blog.setStatus("삭제요청");
		blogRepository.save(blog);
	}
	
	//블로그 삭제 처리
	@Transactional
	public void removeBlog(int blogId) {
		blogRepository.deleteById(blogId);
		categoryRepository.deleteByBlogId(blogId);
		postRepository.deleteByBlogId(blogId);	
	}
}
