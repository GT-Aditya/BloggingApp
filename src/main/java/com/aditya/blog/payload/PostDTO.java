package com.aditya.blog.payload;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

	private Integer id;
	private String title;
	private String content;
	private String imageName;
	private LocalDate createAt;
	private CategoryDTO category;
	private UserDTO user;
	private List<CommentDTO> comments;

}
