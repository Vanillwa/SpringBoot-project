package com.vanillwa.sbp.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vanillwa.sbp.domain.Post;
import com.vanillwa.sbp.dto.PostDTO;
import com.vanillwa.sbp.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class PostRestController {
	
	private final PostService postService;
	
	@ResponseBody
	@PostMapping("/post")
	public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO) {
		Post post = postService.createPost(postDTO);
		return ResponseEntity.ok(post.getPost_id().toString());
	}
}
