package com.blog.biz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import com.blog.biz.domain.Category;
import com.blog.biz.dto.CategoryDto;
import com.blog.biz.persistence.CategoryRepository;
import com.blog.biz.persistence.PostRepository;

@Transactional
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
	
	@InjectMocks
	private CategoryService categoryService;
	
	@Mock
	private CategoryRepository categoryRepository;
	@Mock
	private PostRepository postRepository;
	@Mock
	private ModelMapper modelMapper;
	
	CategoryDto categoryDto;
	Category category;
	
	@BeforeEach
	void beforeEach() {
		categoryDto = CategoryDto.builder()
					.blogId(1)
					.categoryName("test")
					.description("test category")
					.displayType("TITLE")
					.build();
		
		category = Category.builder()
				.blogId(1)
				.categoryName("test")
				.description("test category")
				.displayType("제목")
				.build();
	}
	
	@DisplayName("카테고리 등록 테스트")
	@Test
	void insertCategoryTest() {
		//given
		final String CHANGED_DISPLAY_TYPE="제목";
		
		when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);
		
		//when
		categoryService.insertCategory(categoryDto);
		
		//then
		verify(categoryRepository, times(1)).save(category);
		assertEquals(category.getDisplayType(), CHANGED_DISPLAY_TYPE);
	}
	
	@DisplayName("카테고리 삭제 테스트")
	@Test
	void deleteCategoryTest() {
		//given
		int categoryId = 1;
		
		//when
		categoryService.deleteCategory(categoryId);
		
		//then
		verify(categoryRepository).deleteById(categoryId);
		verify(postRepository).deleteByCategoryId(categoryId);
	}
	
	@DisplayName("카테고리 목록 조회 테스트")
	@Test
	void getCategoryListTest() {
		//given
		int blogId=2;
		List<Category> categoryList = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			Category category = Category.builder()
					.blogId(2)
					.categoryName("test"+i)
					.description("test category"+i)
					.displayType("제목")
					.build();
			categoryList.add(category);
		}
		
		when(categoryRepository.findByBlogIdOrderByCategoryIdDesc(blogId))
			.thenReturn(categoryList);
		
		//when
		List<CategoryDto> findCategories = categoryService.getCategoryList(blogId);
		
		//then
		final int CATEGORY_SIZE=10; 
		assertEquals(findCategories.size(), CATEGORY_SIZE);
	}
}
