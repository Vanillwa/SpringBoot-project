package com.vanillwa.sbp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vanillwa.sbp.auth.UserPrincipalDetails;
import com.vanillwa.sbp.domain.Post;
import com.vanillwa.sbp.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {

	private final PostRepository postRepository;

	@GetMapping(value = { "/", "" })
	public String index(Model model, @AuthenticationPrincipal UserPrincipalDetails user) {
		List<Post> list = postRepository.findAll();
		model.addAttribute("postList", list);
		model.addAttribute("user", user);
		return "/post/posts";
	}

	@GetMapping("/create")
	public String postCreateForm(Model model, @AuthenticationPrincipal UserPrincipalDetails user) {

		model.addAttribute("user", user);
		return "/post/postCreateForm";
	}

	@GetMapping("/{post_id}")
	public String postView(Model model, @PathVariable(name = "post_id") Long post_id,
			@AuthenticationPrincipal UserPrincipalDetails user) {

		Optional<Post> post = postRepository.findById(post_id);
		if (post.isPresent())
			model.addAttribute("post", post.get());

		model.addAttribute("user", user);
		return "/post/postView";
	}
}
