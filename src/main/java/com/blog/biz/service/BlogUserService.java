 package com.blog.biz.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.biz.domain.BlogUser;
import com.blog.biz.dto.BlogUserDto;
import com.blog.biz.persistence.BlogUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BlogUserService {
		
	private final BlogUserRepository blogUserRepository;
	private final ModelMapper modelMapper;
	
	//로그인
	@Transactional(readOnly=true)
	public BlogUserDto login(BlogUserDto blogUserDto) {
		// 유저네임으로 객체를 찾아서 없으면 새로운 비어있는 객체를 리턴한다.
		Optional<BlogUser> findUser = blogUserRepository.findByUsername(blogUserDto.getUsername());
		if(findUser.isPresent()) {
			if(findUser.get().getPassword().equals(blogUserDto.getPassword())) {
				BlogUserDto loginedUserDto = modelMapper.map(findUser.get(), BlogUserDto.class);
				return loginedUserDto;
			}
		}
		return blogUserDto;
	}
}
