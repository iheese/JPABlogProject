package com.blog.biz.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BlogDto {

	private Integer blogId; // 블로그 ID 
	
	private String status; //블로그 상태
	
	private String tag; //블로그 태그
	
	private String title; //블로그 제목
	
	@Builder
	private BlogDto(Integer blogId, String status, String tag, String title) {
		this.blogId=blogId;
		this.status=status;
		this.tag=tag;
		this.title=title;
	}
}
