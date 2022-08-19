package com.blog.biz.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.biz.domain.Post;
import com.blog.biz.dto.PostDto;
import com.blog.biz.persistence.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	
	private final PostRepository postRepository;
	private final ModelMapper modelMapper;
	
	//포스트 등록
	@Transactional
	public void insertPost(PostDto postDto) {
		Post post = modelMapper.map(postDto, Post.class);
		postRepository.save(post);
	}
	
	//포스트 수정
	@Transactional
	public void updatePost(PostDto postDto) {
		Post post = modelMapper.map(postDto, Post.class);
		postRepository.save(post);
	}
	
	//포스트 삭제
	@Transactional
	public void deletePost(int postId) {
		postRepository.deleteById(postId);
	}
	
	//카테고리 삭제시 해당 포스트 삭제
	@Transactional
	public void deletePostByCategory(int categoryId) {
		postRepository.deleteByCategoryId(categoryId);
	}
	
	//포스트 상세 조회
	@Transactional(readOnly=true)
	public PostDto getPost(int postId) {
		Optional<Post> post = postRepository.findById(postId);
		PostDto postDto = modelMapper.map(post.get(),PostDto.class);
		return postDto;
	}
	
	//블로그 내 모든 포스트 목록 조회
	@Transactional(readOnly=true)
	public List<PostDto> getPostListByBlog(int blogId){
		List<Post> postList = postRepository.findByBlogIdOrderByPostIdDesc(blogId);
		List<PostDto> postDtoList = postList.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;
	}
	
	//블로그 내 카테고리별 포스트 목록 조회
	@Transactional(readOnly=true)
	public List<PostDto> getPostListByCategory(int categoryId){
		List<Post> postList = postRepository.findByCategoryIdOrderByPostIdDesc(categoryId);
		List<PostDto> postDtoList = postList.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;
	}
}
