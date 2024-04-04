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
import com.vanillwa.sbp.domain.User;
import com.vanillwa.sbp.repository.UserRepository;
import com.vanillwa.sbp.service.PostService;
import com.vanillwa.sbp.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
	
	private final UserService userService;
	private final UserRepository userRepository;
	private final PostService postService;
	
	@GetMapping("/mypage")
	public String userPage(Model model, @AuthenticationPrincipal UserPrincipalDetails user ) {
		if(user != null)
			model.addAttribute("user", user.getUser());
		return "/user/mypage";
	}
	
	@GetMapping("/{userId}")
	public String userPage(Model model,@PathVariable(name = "userId") Long userId, @AuthenticationPrincipal UserPrincipalDetails user) {
		Optional<User> userData = userRepository.findById(userId);
		if(userData.isPresent()) {
			List<Post> postList = postService.findAllByUser_UserId(userId);
			model.addAttribute("userData", userData.get());
			model.addAttribute("postList", postList);
		}
		
		if (user != null)
			model.addAttribute("user", user.getUser());	
		return "/user/recent";
	}
	
	
}
