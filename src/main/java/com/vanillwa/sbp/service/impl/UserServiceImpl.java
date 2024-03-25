package com.vanillwa.sbp.service.impl;

import org.springframework.stereotype.Service;

import com.vanillwa.sbp.repository.UserRepository;
import com.vanillwa.sbp.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	final UserRepository userRepository;

	@Transactional
	@Override
	public boolean checkUsernameDuplication(String username) {
		boolean usernameDuplicate = userRepository.existsByUsername(username);
		return usernameDuplicate;
	}

}
