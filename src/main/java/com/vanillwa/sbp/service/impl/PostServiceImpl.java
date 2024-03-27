package com.vanillwa.sbp.service.impl;

import org.springframework.stereotype.Service;

import com.vanillwa.sbp.domain.Post;
import com.vanillwa.sbp.dto.PostDTO;
import com.vanillwa.sbp.repository.PostRepository;
import com.vanillwa.sbp.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	final PostRepository postRepository;

	@Override
	public Post createPost(PostDTO postDTO) {
		Post post = new Post();
		
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setUserId(postDTO.getUser_id());
		return postRepository.save(post);
	}
}
