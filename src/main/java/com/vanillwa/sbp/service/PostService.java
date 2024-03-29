package com.vanillwa.sbp.service;

import org.springframework.data.domain.Page;

import com.vanillwa.sbp.domain.Post;
import com.vanillwa.sbp.dto.PostDTO;

public interface PostService {
	public Post createPost(PostDTO postDTO);
	public void deletePost(Long post_id);
	public Post updatePost(PostDTO postDTO);
	public Page<Post> getPosts(int page, int pageSize);
}
