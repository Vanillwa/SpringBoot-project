package com.vanillwa.sbp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	@NotBlank(message = "username은 필수값입니다.")
	private String username;
	
	@NotBlank(message = "password는 필수값입니다.")
	private String password;
}
