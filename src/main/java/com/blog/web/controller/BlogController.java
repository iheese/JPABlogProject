package com.blog.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.biz.dto.BlogDto;
import com.blog.biz.service.BlogService;
import com.blog.biz.service.CategoryService;
import com.blog.biz.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BlogController {
	
	private final BlogService blogService;
	private final PostService postService;
	private final CategoryService categoryService;
	
	//블로그 등록 화면으로 이동
	@GetMapping("/blog")
	public String insertBlogView() {
		return "blog/insertBlog";
	}
	
	//블로그 생성
	@PostMapping("/blog")
	public String insertBlog(BlogDto blogDto, HttpSession session) {
		session.setAttribute("blog", blogService.insertBlog(blogDto));
		return "redirect:/";
	}
	
	//블로그 메인 화면으로 이동
	@GetMapping("/blogmain/{blogId}")
	public String blogMainView(@PathVariable int blogId, Model model) {
		model.addAttribute("blog",blogService.getBlog(blogId));
		model.addAttribute("postList", postService.getPostListByBlog(blogId));
		model.addAttribute("categoryList", categoryService.getCategoryList(blogId));
		return "blog/blogMain";
	}
	
	//메인 화면 내 카테고리별 포스트 출력
	@GetMapping("/blogmain/{blogId}/{categoryId}")
	public String blogByCategoryView(@PathVariable int blogId, @PathVariable int categoryId, Model model) {
		model.addAttribute("blog",blogService.getBlog(blogId));
		model.addAttribute("postListByCategory", postService.getPostListByCategory(categoryId));
		model.addAttribute("categoryList", categoryService.getCategoryList(blogId));
		return "blog/blogMain";
	}
	
	//블로그 관리 페이지 이동
	@GetMapping("/blog/{blogId}/setting")
	public String blogSettingView(@PathVariable int blogId, Model model) {
		model.addAttribute("blog",blogService.getBlog(blogId));
		return "blog/getBlog";
	}
	
	//블로그 수정
	@PostMapping("/blog/{blogId}/setting")
	public String blogSetting(@PathVariable int blogId, BlogDto blogDto) {
		blogService.updateBlog(blogDto);
		return "redirect:/blogmain/" + blogId;
	}
	
	//블로그 삭제 요청 화면 이동
	@GetMapping("/blog/removal/request/{blogId}")
	public String removeBlogView(@PathVariable int blogId, Model model) {
		model.addAttribute("blogId", blogId);
		return "blog/deleteRequest";
	}
	
	//블로그 삭제 요청
	@PostMapping("/blog/removal/request/{blogId}")
	public String removeApplyBlog(@PathVariable int blogId) {
		blogService.removeApplyBlog(blogId);
		return "redirect:/logout";
	}
	
	//블로그 삭제
	@GetMapping("/blog/removal/{blogId}")
	public String removeBlog(@PathVariable int blogId) {
		blogService.removeBlog(blogId);
		return "redirect:/";
	}
}
