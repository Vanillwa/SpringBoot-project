package com.vanillwa.sbp.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vanillwa.sbp.domain.User;
import com.vanillwa.sbp.dto.UserDTO;
import com.vanillwa.sbp.repository.UserRepository;
import com.vanillwa.sbp.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	final UserRepository userRepository;

	@Override
	public boolean isDuplicateUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean isDuplicateNickname(String nickname) {
		return userRepository.existsByNickname(nickname);
	}

	@Override
	public void createUser(UserDTO userDTO) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setNickname(userDTO.getNickname());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

		userRepository.save(user);
	}

}
