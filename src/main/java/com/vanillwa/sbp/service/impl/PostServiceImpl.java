package com.vanillwa.sbp.service.impl;

import org.springframework.stereotype.Service;

import com.vanillwa.sbp.domain.Post;
import com.vanillwa.sbp.dto.PostDTO;
import com.vanillwa.sbp.repository.PostRepository;
import com.vanillwa.sbp.service.PostService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	final PostRepository postRepository;
	final EntityManager entityManager;

	@Override
	public Post createPost(PostDTO postDTO) {
		Post post = new Post();
		
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setUserId(postDTO.getUser_id());
		return postRepository.save(post);
	}

	@Override
	public void deletePost(Long post_id) {
		postRepository.deleteById(post_id);
	}

	@Override
	public Post updatePost(PostDTO postDTO) {
		Post post = entityManager.find(Post.class, postDTO.getPost_id());
		
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		
		return post;
	}
}
