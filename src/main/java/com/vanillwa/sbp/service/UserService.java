package com.vanillwa.sbp.service;

import com.vanillwa.sbp.dto.UserDTO;

public interface UserService {
	boolean isDuplicateUsername(String username);
	boolean isDuplicateNickname(String nickname);
	void createUser(UserDTO userDTO);
}
