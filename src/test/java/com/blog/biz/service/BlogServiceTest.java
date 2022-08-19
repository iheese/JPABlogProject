package com.blog.biz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import com.blog.biz.domain.Blog;
import com.blog.biz.domain.Category;
import com.blog.biz.dto.BlogDto;
import com.blog.biz.persistence.BlogRepository;
import com.blog.biz.persistence.CategoryRepository;
import com.blog.biz.persistence.PostRepository;

@Transactional
@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {
	
	@InjectMocks
	private BlogService blogService;
	
	@Mock
	private BlogRepository blogRepository;
	@Mock
	private CategoryRepository categoryRepository;
	@Mock
	private PostRepository postRepository;
	@Mock
	private ModelMapper modelMapper;
	
	BlogDto saveBlogDto;
	Blog saveBlog;
	
	@BeforeEach
	void beforeEach() {
		saveBlogDto=BlogDto.builder()
					.blogId(1)
					.status("운영")
					.tag("NO tags")
					.title("제목1")
					.build();
	
		saveBlog=Blog.builder()
					.blogId(1)
					.status("운영")
					.tag("NO tags")
					.title("제목1")
					.build();
	}
	
	@DisplayName("블로그 생성 테스트")
	@Test
	void insertBlogTest() {
		//given
		when(modelMapper.map(saveBlogDto, Blog.class)).thenReturn(saveBlog);
		
		//when
		BlogDto blogDto = blogService.insertBlog(saveBlogDto);
		
		//then
		//초기 설정된 값과 저장 객체가 일치하는지
		assertEquals(blogDto, saveBlogDto);
		
		//각 Repository들의 save가 1번 호출된 것이 맞는지
		verify(blogRepository).save(saveBlog); 
		verify(categoryRepository).save(any(Category.class));
	}
	
	@DisplayName("블로그 상세 조회 테스트")
	@Test
	void getBlogTest() {
		//given
		when(blogRepository.findById(1)).thenReturn(Optional.of(saveBlog));
		when(modelMapper.map(saveBlog, BlogDto.class)).thenReturn(saveBlogDto);
		
		///when
		BlogDto rightBlog = blogService.getBlog(1);
		
		//then
		assertEquals(rightBlog, saveBlogDto);
	}
	
	@DisplayName("블로그 삭제 테스트")
	@Test
	void removeBlogTest() {
		//given
		doNothing().when(blogRepository).deleteById(anyInt());
		doNothing().when(categoryRepository).deleteByBlogId(anyInt());
		doNothing().when(postRepository).deleteByBlogId(anyInt());
		
		//when
		blogService.removeBlog(1);
		
		//then
		verify(blogRepository,times(1)).deleteById(1);
		verify(categoryRepository,times(1)).deleteByBlogId(1);
		verify(postRepository,times(1)).deleteByBlogId(1);
	}
	
}
