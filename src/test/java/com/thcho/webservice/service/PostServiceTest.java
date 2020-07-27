package com.thcho.webservice.service;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.thcho.webservice.domain.Posts;
import com.thcho.webservice.domain.PostsRepository;
import com.thcho.webservice.dto.PostsSaveRequestDto;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class PostServiceTest {

	@Autowired
	private PostsService postsService;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@AfterAll
	public void cleanUp() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void Dto데이터가_posts테이블에_저장된다() {
		
		//given
		PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
				.author("thcho80@gmail.com")
				.content("테스트")
				.title("테스트타이틀")
				.build();
		
		//when
		postsService.save(dto);
		
		//then
		Posts posts = postsRepository.findAll().get(0);
		assertEquals(posts.getAuthor(), "thcho80@gmail.com");
		assertEquals(posts.getContent(), "테스트");
		assertEquals(posts.getTitle(), "테스트타이틀");
				
	}
}
