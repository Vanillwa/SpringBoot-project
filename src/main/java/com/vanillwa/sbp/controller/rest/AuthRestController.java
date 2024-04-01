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

	private final UserService userService;
	private final HttpSession session;

	@GetMapping("/usernameOnchange")
	public ResponseEntity<String> usernameOnchange() {
		session.removeAttribute("isDuplicateUsername");
		return ResponseEntity.ok("ok");
	}

	// username 중복 체크
	@PostMapping("/checkUsernameDuplication")
	public ResponseEntity<Boolean> checkUsernameDuplication(@RequestBody UserDTO userDTO) {
		boolean check = userService.isDuplicateUsername(userDTO.getUsername());
		session.setAttribute("isDuplicateUsername", check);
		return ResponseEntity.ok(check);
	}

	@GetMapping("/nicknameOnchange")
	public ResponseEntity<String> nicknameOnchange() {
		session.removeAttribute("isDuplicateNickname");
		return ResponseEntity.ok("ok");
	}

	// nickname 중복 체크
	@PostMapping("/checkNicknameDuplication")
	public ResponseEntity<Boolean> checkNicknameDuplication(@RequestBody UserDTO userDTO) {
		boolean check = userService.isDuplicateNickname(userDTO.getNickname());
		session.setAttribute("isDuplicateNickname", check);
		return ResponseEntity.ok(check);
	}

	// 회원 가입
	@PostMapping("/createUser")
	public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
		if (session.getAttribute("isDuplicateUsername") == null)
			return ResponseEntity.ok("unCheckedUsername");
		
		if (session.getAttribute("isDuplicateNickname") == null)
			return ResponseEntity.ok("unCheckedNickname");

		if (userService.isDuplicateUsername(userDTO.getUsername())) {
			session.removeAttribute("isDuplicateUsername");
			return ResponseEntity.ok("duplicatedUsername");
		}
		
		if (userService.isDuplicateNickname(userDTO.getNickname())) {
			session.removeAttribute("isDuplicateNickname");
			return ResponseEntity.ok("duplicatedNickname");
		}

		userService.createUser(userDTO);

		session.removeAttribute("isDuplicateUsername");
		return ResponseEntity.ok("success");
	}
}
