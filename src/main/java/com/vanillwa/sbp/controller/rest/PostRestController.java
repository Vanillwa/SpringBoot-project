package com.vanillwa.sbp.controller.rest;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanillwa.sbp.auth.UserPrincipalDetails;
import com.vanillwa.sbp.domain.Post;
import com.vanillwa.sbp.dto.PostDTO;
import com.vanillwa.sbp.repository.PostRepository;
import com.vanillwa.sbp.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class PostRestController {

	private final PostService postService;
	private final PostRepository postRepository;

	@PostMapping("/post")
	public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO,
			@AuthenticationPrincipal UserPrincipalDetails user) {
		postDTO.setUser_id(user.getUser().getUser_id());
		Post post = postService.createPost(postDTO);
		return ResponseEntity.ok(post.getPost_id().toString());
	}

	@DeleteMapping("/post/{post_id}")
	public ResponseEntity<String> deletePost(@PathVariable(name = "post_id") Long post_id,
			@AuthenticationPrincipal UserPrincipalDetails user) {
		Optional<Post> post = postRepository.findById(post_id);
		if (!post.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NoExist");

		if (user.getUser().getRole().equals("ADMIN")
				|| post.get().getUser().getUser_id() == user.getUser().getUser_id()) {
			postService.deletePost(post_id);
			return ResponseEntity.ok("success");
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("NoAuth");
		}
	}

	@PutMapping("/post")
	public ResponseEntity<String> updatePost(@RequestBody PostDTO postDTO,
			@AuthenticationPrincipal UserPrincipalDetails user) {
		Post post = postService.updatePost(postDTO);
		
		if(post == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NoExist");
		
		return ResponseEntity.ok("success");
	}
}
