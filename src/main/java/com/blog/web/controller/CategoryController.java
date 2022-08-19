package com.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.biz.dto.CategoryDto;
import com.blog.biz.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CategoryController {

	private final CategoryService categoryService;
	
	//카테고리 목록 조회
	@GetMapping("/categories/{blogId}")
	public String categoryView(@PathVariable int blogId, Model model) {
		model.addAttribute("categoryList",categoryService.getCategoryList(blogId));
		return "category/getCategoryList";
	}
	
	//카테고리 등록
	@PostMapping("/categories/{blogId}")
	public String insertCatregory(@PathVariable int blogId, CategoryDto categoryDto) {
		categoryService.insertCategory(categoryDto);
		return "redirect:/categories/"+blogId;
	}
	
	//카테고리 수정화면 전환
	@GetMapping("/categories/category/{categoryId}")
	public String updateCategoryView(@PathVariable int categoryId, Model model) {
		CategoryDto findCategory = categoryService.getCategory(categoryId);
		model.addAttribute("updateCategory",findCategory);
		return "forward:/categories/" + findCategory.getBlogId();
	}
	
	//카테고리 수정
	@PostMapping("/categories/category")
	public String updateCategory(CategoryDto categoryDto) {
		categoryService.updateCategory(categoryDto);
		return  "redirect:/categories/"+ categoryDto.getBlogId();
	}
	
	//카테고리 삭제
	@GetMapping("/category")
	public String deleteCategory(@RequestParam int blogId, @RequestParam int categoryId) {
		categoryService.deleteCategory(categoryId);
		return "forward:/categories/"+blogId;
	}
}
