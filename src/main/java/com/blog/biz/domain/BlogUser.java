package com.blog.biz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class BlogUser {
	@Id
	private int userId; //블로그 유저 Id, 유저 데이터는 주어진 상태에서 시작하므로 원시타입 설정
	
	@Column(nullable = false, unique = true)
	private String username; //블로그 유저 아이디
	
	@Column(nullable = false)
	private String password; //블로그 유저 비밀번호

	@Column(nullable = false)
	private String name; //블로그 유저 이름
	
	@Column(nullable = false)
	private String role; //블로그 유저 권한
	
	@Builder
	private BlogUser(int userId, String username, String password) {
		this.userId=userId;
		this.username=username;
		this.password=password;
	}
}
