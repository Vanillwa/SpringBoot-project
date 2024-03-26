package com.vanillwa.sbp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vanillwa.sbp.auth.UserPrincipalDetails;

@Controller
@RequestMapping(value = "/posts")
public class PostsController {
	@GetMapping(value = {"/", ""})
	public String index(Model model, @AuthenticationPrincipal UserPrincipalDetails user) {
		model.addAttribute("user", user);
		return "/posts/posts";
	}
}
