package com.blog.biz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.biz.domain.Category;
import com.blog.biz.dto.CategoryDto;
import com.blog.biz.persistence.CategoryRepository;
import com.blog.biz.persistence.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final PostRepository postRepository;
	private final ModelMapper modelMapper;
	
	//카테고리 등록
	@Transactional
	public void insertCategory(CategoryDto categoryDto) {
		// 카테고리 displayType value 변경해서 저장
		if(categoryDto.getDisplayType().equals("TITLE")) {
			categoryDto.setDisplayType("제목");
		} 
		if(categoryDto.getDisplayType().equals("MIXED")){
			categoryDto.setDisplayType("제목 + 내용");
		}
		Category category = modelMapper.map(categoryDto, Category.class);
		categoryRepository.save(category);
	}
	
	//카테고리 수정
	@Transactional
	public void updateCategory(CategoryDto categoryDto) {
		// 카테고리 displayType value 확인후 저장
		if(categoryDto.getDisplayType().equals("TITLE")) {
			categoryDto.setDisplayType("제목");
		} 
		if(categoryDto.getDisplayType().equals("MIXED")){
			categoryDto.setDisplayType("제목 + 내용");
		}
		Category category = modelMapper.map(categoryDto, Category.class);
		categoryRepository.save(category);
	}
	
	//카테고리 삭제
	@Transactional
	public void deleteCategory(int categoryId) {
		categoryRepository.deleteById(categoryId);
		postRepository.deleteByCategoryId(categoryId);
	}
	
	//카테고리 목록 조회
	@Transactional(readOnly=true)
	public List<CategoryDto> getCategoryList(int blogId){
		List<Category> categoryList =categoryRepository.findByBlogIdOrderByCategoryIdDesc(blogId);
		List<CategoryDto> categoryDtoList = categoryList.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtoList;
	}
	
	//카테고리 상세 조회
	@Transactional(readOnly=true)
	public CategoryDto getCategory(int categoryId){
		Category category = categoryRepository.findById(categoryId).orElseGet(Category::new);
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}
}
