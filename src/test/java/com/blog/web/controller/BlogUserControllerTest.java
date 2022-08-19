package com.blog.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import com.blog.biz.dto.BlogDto;
import com.blog.biz.dto.BlogUserDto;
import com.blog.biz.service.BlogService;
import com.blog.biz.service.BlogUserService;

@WebMvcTest(BlogUserController.class)
@MockBean(JpaMetamodelMappingContext.class) //@EnableJpaAuditin을 사용하면서 jpa 빈을 필요로 하여 mockbean 생성으로 처리해주었다. 
public class BlogUserControllerTest {
	
	@MockBean
	private BlogService blogService;
	@MockBean
	private BlogUserService blogUserService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@DisplayName("로그인 테스트")
	@Test
	void loginTest() throws Exception {
		//given
		BlogUserDto blogUserDto =BlogUserDto.builder()
							.userId(2)
							.username("test")
							.password("test111")
							.build();
		
		BlogDto blogDto =BlogDto.builder()
					.blogId(2)
					.status("운영")
					.tag("test")
					.title("test")
					.build();
		
		when(blogUserService.login(any(BlogUserDto.class))).thenReturn(blogUserDto);
		when(blogService.getBlog(blogUserDto.getUserId())).thenReturn(blogDto);
		
		//when
		mockMvc.perform(post("/login"))
		
		//then
				.andExpect(redirectedUrl("/")) //리다이렉션이 잘되었는지
				.andDo(print()); //요청 응답 과정 출력
	}
}
