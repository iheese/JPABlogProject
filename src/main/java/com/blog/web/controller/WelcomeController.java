package com.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.biz.service.BlogService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WelcomeController {
	
	private final BlogService blogService;
	
	//웰컴페이지
	@GetMapping({"", "/"})
	public String welcome(Model model) {
		model.addAttribute("blogList", blogService.getBlogList());
		return "welcome";
	}
	
	//웰컴페이지 내 검색 화면 출력
	@PostMapping("/")
	public String welcomeSearchView(@RequestParam String searchKeyword,
			@RequestParam String searchCondition, Model model) {
		model.addAttribute("searchBlogList",blogService.searchBlog(searchCondition, searchKeyword));
		model.addAttribute("searchCondition", searchCondition);
		return "welcome";
	}
}
