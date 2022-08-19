package com.blog.biz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import com.blog.biz.domain.BlogUser;
import com.blog.biz.dto.BlogUserDto;
import com.blog.biz.persistence.BlogUserRepository;

@Transactional
@ExtendWith(MockitoExtension.class)
public class BlogUserServiceTest {

	@InjectMocks
	private BlogUserService blogUserService;

	@Mock
	private BlogUserRepository blogUserRepository;
	@Mock
	private ModelMapper modelMapper;
	
	@DisplayName("로그인 테스트")
	@Test
	void loginTest() {
		//given
		BlogUserDto blogUserDto = BlogUserDto.builder()
							.userId(1)
							.username("test111")
							.password("test222")
							.build();

		BlogUser blogUser = BlogUser.builder()
								.userId(1)
								.username("test111")
								.password("test222")
								.build();
		
		when(blogUserRepository.findByUsername(blogUser.getUsername())).thenReturn(Optional.ofNullable(blogUser));
		when(modelMapper.map(blogUser, BlogUserDto.class)).thenReturn(blogUserDto);
		
		//when
		BlogUserDto loginUser=blogUserService.login(blogUserDto);
		
		//then
		assertEquals(loginUser, blogUserDto);
	}
}
