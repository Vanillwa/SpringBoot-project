package com.vanillwa.sbp.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vanillwa.sbp.auth.UserPrincipalDetails;
import com.vanillwa.sbp.domain.Post;
import com.vanillwa.sbp.repository.PostRepository;
import com.vanillwa.sbp.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {

	private final PostService postService;
	private final PostRepository postRepository;

	@GetMapping(value = { "/", "" })
	public String index(Model model, @AuthenticationPrincipal UserPrincipalDetails user,
			@RequestParam(required = false, defaultValue = "1", name = "page") int page) {
		
		Page<Post> paging = postService.getPosts(page-1, 15);
		
		int startPage, lastPage, totalPage = paging.getTotalPages();
		
		if(page < 3) {
			startPage = 1;
			lastPage = 5;
		}else if(page > totalPage-2) {
			startPage = totalPage-4;
			lastPage = totalPage;
		}else {
			startPage = page-2;
			lastPage = page+2;
		}
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("paging", paging);

		if (user != null)
			model.addAttribute("user", user.getUser());
		return "/post/posts";
	}

	@GetMapping("/create")
	public String postCreateForm(Model model, @AuthenticationPrincipal UserPrincipalDetails user) {

		model.addAttribute("user", user.getUser());
		return "/post/postCreateForm";
	}

	@GetMapping("/{postId}")
	public String postView(Model model, @PathVariable(name = "postId") Long postId,
			@AuthenticationPrincipal UserPrincipalDetails user) {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isPresent()) {
			model.addAttribute("post", post.get());
		}
		if (user != null)
			model.addAttribute("user", user.getUser());
		return "/post/postView";
	}

	@GetMapping("/{postId}/update")
	public String postUpdateForm(Model model, @PathVariable(name = "postId") Long postId,
			@AuthenticationPrincipal UserPrincipalDetails user) {
		Optional<Post> post = postRepository.findById(postId);

		if (!post.isPresent() || post.get().getUser().getUserId() != user.getUser().getUserId()) {
			model.addAttribute("message", "잘못된 접근입니다.");
			return "/common/alertRedirect";
		}

		if (post.isPresent())
			model.addAttribute("post", post.get());
		if (user != null)
			model.addAttribute("user", user.getUser());

		return "/post/postUpdateForm";
	}
}
