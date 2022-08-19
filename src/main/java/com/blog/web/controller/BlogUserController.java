package com.blog.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.biz.dto.BlogDto;
import com.blog.biz.dto.BlogUserDto;
import com.blog.biz.service.BlogService;
import com.blog.biz.service.BlogUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BlogUserController {
	
	private final BlogUserService blogUserService;
	private final BlogService blogService;
	
	//로그인 화면으로 이동
	@GetMapping("/login")
	public String loginView() {
		return "system/login";
	}
	
	//로그인
	@PostMapping("/login")
	public String login(BlogUserDto blogUserDto, HttpSession session) {
			BlogUserDto findUser=blogUserService.login(blogUserDto);
			BlogDto findBlog = blogService.getBlog(findUser.getUserId());
			// 세션으로 전달한다. 성공하든 실패하든 웰컴페이지로 이동
			if(findUser.getUserId() != 0) {
				session.setAttribute("user", findUser);
				session.setAttribute("blog", findBlog);
				return "redirect:/";
			}
			return "redirect:/";
		}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
