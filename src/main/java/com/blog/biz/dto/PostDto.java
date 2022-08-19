package com.blog.biz.dto;

import com.blog.biz.domain.date.BaseTime;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false) //부모 필드 체크 안함
public class PostDto extends BaseTime{
	
	private Integer postId; //포스트 Id
	
	private Integer blogId; //블로그 Id
	
	private Integer categoryId; //카테고리 Id
	
	private String title; //포스트 제목
	
	private String content; //포스트 내용
	
	//생성 날짜, 수정 날짜를 BaseTime 클래스로부터 상속받아 사용하여 생략하였다. 
	
	@Builder
	private PostDto(Integer blogId, Integer categoryId, String title, String content) {
		this.blogId=blogId;
		this.categoryId=categoryId;
		this.title=title;
		this.content=content;
	}
}
