package com.vanillwa.sbp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

	@GetMapping("/login")
	public String loginForm() {
		return "/auth/loginForm";
	}

	@GetMapping("/signup")
	public String signupForm() {
		return "/auth/signupForm";
	}
}
