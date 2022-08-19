package com.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.biz.dto.PostDto;
import com.blog.biz.service.CategoryService;
import com.blog.biz.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {
	
	private final PostService postService;
	private final CategoryService categoryService;
	
	//포스트 등록 화면으로 이동 
	@GetMapping("/post/{blogId}")
	public String insertPostView(@PathVariable int blogId, Model model) {
		model.addAttribute("categoryList",categoryService.getCategoryList(blogId));
		model.addAttribute("blogId", blogId);
		return "post/insertPost";
		}
	
	//포스트 등록
	@PostMapping("/post/{blogId}")
	public String insertPost(PostDto postDto) {
		postService.insertPost(postDto);
		return "redirect:/blogmain/" + postDto.getBlogId();
		}
	
	//포스트 수정 화면 이동
	@GetMapping("/posts/post/{postId}")
	public String updatePost(@PathVariable int postId, Model model) {
		PostDto findPost =postService.getPost(postId);
		model.addAttribute("categoryList",categoryService.getCategoryList(findPost.getBlogId()));
		model.addAttribute("post", findPost);
		return "post/getPost";
	}
	
	//포스트 수정
	@PostMapping("/posts/post/{postId}")
	public String updatePost(@PathVariable int postId, PostDto postDto) {
		postService.updatePost(postDto);
		return "redirect:/blogmain/" + postDto.getBlogId();
	}
	
	//포스트 삭제
	@GetMapping("/posts/{blogId}")
	public String deletePost(@PathVariable int blogId, @RequestParam int postId) {
		postService.deletePost(postId);
		return "redirect:/blogmain/" + blogId;
	}
}