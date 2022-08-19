package com.blog.web.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import com.blog.biz.dto.BlogDto;
import com.blog.biz.service.BlogService;

@WebMvcTest(WelcomeController.class)
@MockBean(JpaMetamodelMappingContext.class) //@EnableJpaAuditin을 사용하면서 jpa 빈을 필요로 하여 mockbean 생성으로 처리해주었다. 
public class WelcomeControllerTest {

	@MockBean
	private BlogService blogService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@DisplayName("웰컴페이지 검색 화면 출력 테스트")
	@Test
	void welcomeSearchViewTest() throws Exception {
		//given
		BlogDto blogDto = BlogDto.builder()
						.blogId(1)
						.status("운영")
						.tag("test")
						.title("test")
						.build();
		
		List<BlogDto> searchBlogDtoList = new ArrayList<>();
		searchBlogDtoList.add(blogDto);
		
		final String searchCondition = "TITLE";
		final String searchKeyword = "test";
		when(blogService.searchBlog(searchCondition,searchKeyword))
								.thenReturn(searchBlogDtoList);
		//when
		mockMvc.perform(post("/")
				.param("searchCondition", searchCondition)
				.param("searchKeyword",searchKeyword)
				)

		//then
				.andExpect(status().isOk()) //상태
				.andExpect(model().attribute("searchBlogList", searchBlogDtoList)) //model에 객체가 잘 담겼는지
				.andExpect(view().name("welcome")); //뷰 이동이 잘되었지
	}
}
