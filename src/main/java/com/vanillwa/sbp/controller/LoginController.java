package com.vanillwa.sbp.controller;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vanillwa.sbp.dto.UserDTO;
import com.vanillwa.sbp.repository.UserRepository;
import com.vanillwa.sbp.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/login")
public class LoginController {
	private final UserRepository userRepository;
	private final UserService userService;

	@GetMapping("/loginForm")
	public String loginForm() {
		return "/login/loginForm";
	}

	@GetMapping("/signupForm")
	public String signupForm() {
		return "/login/signupForm";
	}

	@ResponseBody
	@PostMapping("/checkUsernameDuplication")
	public ResponseEntity<Boolean> checkUsernameDuplication(@RequestBody UserDTO userDTO) {
		System.out.println("dddddddddddddddddddddddddddd "+ userDTO.getUsername());
		return ResponseEntity.ok(userService.checkUsernameDuplication(userDTO.getUsername()));
	}

}
