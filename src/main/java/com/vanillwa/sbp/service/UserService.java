package com.vanillwa.sbp.service;

import com.vanillwa.sbp.dto.UserDTO;

public interface UserService {
	boolean checkUsernameDuplication(String username);
	void createUser(UserDTO userDTO);
}
