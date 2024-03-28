package com.vanillwa.sbp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	private Long post_id;
	private String title;
	private String content;
	private Long user_id;
}
