 package com.blog.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.blog.biz.dto.BlogDto;
import com.blog.biz.dto.CategoryDto;
import com.blog.biz.dto.PostDto;
import com.blog.biz.service.BlogService;
import com.blog.biz.service.CategoryService;
import com.blog.biz.service.PostService;

@WebMvcTest(BlogController.class)
@MockBean(JpaMetamodelMappingContext.class) //@EnableJpaAuditin을 사용하면서 jpa 빈을 필요로 하여 mockbean 생성으로 처리해주었다. 
public class BlogControllerTest {
	
	@MockBean
	private BlogService blogService;
	@MockBean
	private PostService postService;
	@MockBean
	private CategoryService categoryService;
	
	@Autowired
	private MockMvc mockMvc;
	
	BlogDto blogDto;
	
	@BeforeEach
	void beforeEach() {
		blogDto = BlogDto.builder()
				.blogId(1)
				.status("운영")
				.tag("test")
				.title("test")
				.build();
	}
	
	@DisplayName("블로그 생성 처리 테스트")
	@Test
	void insertBlogTest() throws Exception {
		//given
		when(blogService.insertBlog(any(BlogDto.class))).thenReturn(blogDto);
		
		//when
		mockMvc.perform(post("/blog")
				.sessionAttr("blog", blogDto)
				)
		//then
				.andExpect(redirectedUrl("/")); //리다이렉션이 잘되었는지
				verify(blogService).insertBlog(any(BlogDto.class)); //메소드가 한 번 실행 되었는지
	}
	
	@DisplayName("블로그 메인 화면 조회 테스트")
	@Test
	void blogMainViewTest() throws Exception {
		//given
		final int blogId = 2;
		List<PostDto> postList = new ArrayList<>();
		List<CategoryDto> categoryList = new ArrayList<>();
		
		PostDto post = PostDto.builder()
					.blogId(blogId)
					.categoryId(1)
					.title("test")
					.content("test")
					.build();
		
		postList.add(post);
		
		CategoryDto category = CategoryDto.builder()
							.blogId(blogId)
							.categoryName("test")
							.description("test")
							.displayType("제목")
							.build();
		
		categoryList.add(category);
		
		when(blogService.getBlog(blogId)).thenReturn(blogDto);
		when(postService.getPostListByBlog(blogId)).thenReturn(postList);
		when(categoryService.getCategoryList(blogId)).thenReturn(categoryList);
		
		//when
		mockMvc.perform(get("/blogmain/"+blogId))
		
		//then
				.andExpect(status().isOk()) //200 상태 코드인지
				.andExpect(model().attribute("blog", blogDto))  //모델에 값들이 잘 담겼는지
				.andExpect(model().attribute("postList", postList))
				.andExpect(model().attribute("categoryList", categoryList));
	}
	
	@DisplayName("블로그 삭제 테스트")
	@Test
	void removeBlogTest() throws Exception {
		//given
		doNothing().when(blogService).removeBlog(anyInt());
		
		//when
		mockMvc.perform(get("/blog/removal/"+anyInt()))
		
		//then
				.andExpect(redirectedUrl("/")); //리다이렉션이 잘되었는지
				verify(blogService).removeBlog(anyInt()); //메소드가 한 번 잘 실행되었는지
	}
	
}
