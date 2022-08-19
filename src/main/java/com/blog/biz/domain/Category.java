package com.blog.biz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId; //카테고리 Id
	
	private Integer blogId; //카테고리가 속한 blogId
	
	private String categoryName; //카테고리 이름
	
	private String description; //카테고리 설명
	
	private String displayType; //제목+내용 /제목, 보는 방법
	
	@Builder
	private Category(Integer blogId, String categoryName, String description, String displayType) {
		this.blogId=blogId;
		this.categoryName=categoryName;
		this.description=description;
		this.displayType=displayType;
	}
}

