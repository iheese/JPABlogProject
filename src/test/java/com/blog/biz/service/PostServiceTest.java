package com.blog.biz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.blog.biz.domain.Post;
import com.blog.biz.dto.PostDto;
import com.blog.biz.persistence.PostRepository;

@Transactional
@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
	
	@InjectMocks
	private PostService postService;
	
	@Mock
	private PostRepository postRepository;
	@Mock
	private ModelMapper modelMapper;
	
	PostDto postDto;
	Post post;
	
	@BeforeEach
	void beforEach() {
		postDto = PostDto.builder()
				.blogId(1)
				.categoryId(1)
				.title("test title")
				.content("test content")
				.build();
		
		post = Post.builder()
				.blogId(1)
				.categoryId(1)
				.title("test title")
				.content("test content")
				.build();
	}
	
	@DisplayName("포스트 추가 태스트")
	@Test
	void insertPostTest() {
		//given
		when(modelMapper.map(postDto, Post.class)).thenReturn(post);
		
		//when
		postService.insertPost(postDto);
		
		//then
		verify(postRepository,times(1)).save(post);
	}
	
	@DisplayName("포스트 삭제 테스트")
	@Test
	void deletePostTest() {
		//given
		int postId=5;
		
		//when
		postService.deletePost(postId);
		
		//then
		verify(postRepository,times(1)).deleteById(postId);
	}
	
	@DisplayName("포스트 상세 조회 테스트")
	@Test
	void getPostTest() {
		//given
		int postId = 1;
		when(postRepository.findById(postId)).thenReturn(Optional.of(post));		
		when(modelMapper.map(post, PostDto.class)).thenReturn(postDto);
		
		//when
		PostDto findPostDto = postService.getPost(postId);		
		
		//then
		assertEquals(findPostDto, postDto);
	}
	
}
