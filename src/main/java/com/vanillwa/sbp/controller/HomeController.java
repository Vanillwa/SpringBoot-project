package com.vanillwa.sbp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vanillwa.sbp.auth.UserPrincipalDetails;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final HttpSession session;
	
	@GetMapping(value = {"/", ""})
	public String index(Model model, @AuthenticationPrincipal UserPrincipalDetails user) {	
		model.addAttribute("user", user);
		return "index";
	}
	
	@GetMapping("/user/userPage")
	public String userPage(Model model, @AuthenticationPrincipal UserPrincipalDetails user ) {
		model.addAttribute("user", user);
		return "/user/userPage";
	}
}
