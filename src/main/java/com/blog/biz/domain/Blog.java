package com.blog.biz.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Blog {
	@Id
	private Integer blogId; // 블로그 ID 
	
	private String status; //블로그 상태
	
	private String tag; //블로그 태그
	
	private String title; //블로그 제목
	
	@Builder
	private Blog(Integer blogId, String status, String tag, String title) {
		this.blogId=blogId;
		this.status=status;
		this.tag=tag;
		this.title=title;
	}
}
