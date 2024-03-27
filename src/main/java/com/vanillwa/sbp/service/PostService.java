package com.vanillwa.sbp.service;

import com.vanillwa.sbp.domain.Post;
import com.vanillwa.sbp.dto.PostDTO;

public interface PostService {
	public Post createPost(PostDTO postDTO);
}
