package com.codewithchhotu.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty(message="pls provide name")
	private String name;
	
	@Email(message="pls use correct email")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10,message="pls enter password within 3 to 10 range")
	private String password;
	
	@NotEmpty(message="pls write something")
	private String about;

}
