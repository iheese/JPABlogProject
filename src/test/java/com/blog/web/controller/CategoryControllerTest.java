package com.blog.web.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.blog.biz.dto.CategoryDto;
import com.blog.biz.service.CategoryService;
import com.blog.biz.service.PostService;

@WebMvcTest(CategoryController.class)
@MockBean(JpaMetamodelMappingContext.class) //@EnableJpaAuditin을 사용하면서 jpa 빈을 필요로 하여 mockbean 생성으로 처리해주었다. 
public class CategoryControllerTest {

	@MockBean
	private CategoryService categoryService;
	@MockBean
	private PostService postService;
	
	@Autowired
	private MockMvc mockMvc;
	
	int blogId;
	CategoryDto categoryDto;
	
	@BeforeEach
	void beforeEach() {
		blogId=1;
		
		categoryDto =CategoryDto.builder()
				  	.blogId(blogId)
					.categoryName("test")
					.description("test")
					.displayType("제목")
					.build();
	}

	
	@DisplayName("카테고리 등록 테스트")
	@Test
	void insertCategoryTest() throws Exception {
		//given
		doNothing().when(categoryService).insertCategory(categoryDto);
	
		//when
		mockMvc.perform(post("/categories/"+blogId))
		
		//then
				.andExpect(redirectedUrl("/categories/"+blogId)); //리다이렉션이 잘되었는지
		
		
	}
	
	@DisplayName("카테고리 목록 조회 테스트")
	@Test
	void categoryViewTest() throws Exception {
		//given
		List<CategoryDto> categoryDtoList = new ArrayList<>();
		categoryDtoList.add(categoryDto);
		
		when(categoryService.getCategoryList(blogId)).thenReturn(categoryDtoList);	
		
		//when
		mockMvc.perform(get("/categories/"+blogId))
		
		//then
				.andExpect(status().isOk()) //200 상태 코드가 나왔는지
		 		.andExpect(view().name("category/getCategoryList")) //view가 잘 생성되었는지
				.andExpect(model().attribute("categoryList", categoryDtoList)); //모델에 값이 잘 담겼는지
	}
	
	@DisplayName("카테고리 삭제 테스트")
	@Test
	void deleteCategoryTest() throws Exception{
		//given
		final int categoryId=2; 
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();	
		map.add("categoryId" , "2");
		map.add("blogId", "1");
		
		doNothing().when(categoryService).deleteCategory(categoryId);
		doNothing().when(postService).deletePostByCategory(categoryId);

		//when
		mockMvc.perform(get("/category")
				.params(map)
				)
		
		//then
				.andExpect(status().isOk()) //200 상태 코드가 잘 나왔는지
				.andExpect(forwardedUrl("/categories/"+blogId)) //포워딩이 잘되었는지
				.andDo(print()); //요청, 응답 과정 출력
	}
}
