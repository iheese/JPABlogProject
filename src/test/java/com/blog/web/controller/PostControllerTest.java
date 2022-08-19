package com.blog.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import com.blog.biz.dto.PostDto;
import com.blog.biz.service.CategoryService;
import com.blog.biz.service.PostService;

@WebMvcTest(PostController.class)
@MockBean(JpaMetamodelMappingContext.class) //@EnableJpaAuditin을 사용하면서 jpa 빈을 필요로 하여 mockbean 생성으로 처리해주었다. 
public class PostControllerTest {

	@MockBean
	private PostService postService;
	@MockBean
	private CategoryService categoryService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@DisplayName("포스트 등록 테스트")
	@Test
	void insertPostTest() throws Exception {
		//given
		final int blogId=2;
		final int categoryId=2;
		
		PostDto savePostDto =PostDto.builder()
							.blogId(blogId)
							.categoryId(categoryId)
							.title("test")
							.content("test")
							.build();
		
		doNothing().when(postService).insertPost(savePostDto);

		//when
		mockMvc.perform(post("/post/"+blogId))
		
		//then
				.andExpect(redirectedUrl("/blogmain/"+blogId)); //리다이렉션이 잘되었는지
		verify(postService).insertPost(any(PostDto.class)); //메소드가 한 번 잘 실행되었는지
	}
	
	@DisplayName("포스트 삭제 테스트")
	@Test
	void deletePostTest() throws Exception {
		//given
		final int blogId=2;
		final int postId=5;
		
		doNothing().when(postService).deletePost(postId);
		
		//when
		mockMvc.perform(get("/posts/"+blogId)
				.param("blogId", "2")
				.param("postId", "5")
				)
		
		//then
				.andExpect(redirectedUrl("/blogmain/"+blogId)); //리다이렉션이 잘되었는지
		verify(postService).deletePost(postId); //삭제 메소드가 잘 호출되었는지
	}
	
}
