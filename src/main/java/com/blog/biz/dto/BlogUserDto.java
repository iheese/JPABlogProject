package com.blog.biz.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BlogUserDto {
	
	private int userId; //블로그 유저 Id, 유저 데이터는 주어진 상태에서 시작하므로 원시타입 설정
	
	private String username; //블로그 유저 아이디
	
	private String password; //블로그 유저 비밀번호

	private String name; //블로그 유저 이름
	
	private String role; //블로그 유저 권한
	
	@Builder
	private BlogUserDto(int userId, String username, String password) {
		this.userId=userId;
		this.username=username;
		this.password=password;
	}
}
