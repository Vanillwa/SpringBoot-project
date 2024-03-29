package com.vanillwa.sbp.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vanillwa.sbp.dto.UserDTO;
import com.vanillwa.sbp.repository.UserRepository;
import com.vanillwa.sbp.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {
	
	private final UserRepository userRepository;
	private final UserService userService;
	private final HttpSession session;
	
	@GetMapping("/usernameOnchange")
	public ResponseEntity<String> usernameOnchange() {
		session.removeAttribute("checkUsernameDuplication");
		return ResponseEntity.ok("ok");
	}
	
	// username 중복 체크
	@ResponseBody
	@PostMapping("/checkUsernameDuplication")
	public ResponseEntity<Boolean> checkUsernameDuplication(@RequestBody UserDTO userDTO) {
		boolean check = userService.checkUsernameDuplication(userDTO.getUsername());
		session.setAttribute("checkUsernameDuplication", check);
		return ResponseEntity.ok(check);
	}

	// 회원 가입
	@PostMapping("/createUser")
	public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
		if (session.getAttribute("checkUsernameDuplication") == null) {
			session.removeAttribute("checkUsernameDuplication");
			return ResponseEntity.ok("unChecked");
		}

		if (userService.checkUsernameDuplication(userDTO.getUsername())) {
			session.removeAttribute("checkUsernameDuplication");
			return ResponseEntity.ok("duplicated");
		}

		userService.createUser(userDTO);

		session.removeAttribute("checkUsernameDuplication");
		return ResponseEntity.ok("success");
	}
}
