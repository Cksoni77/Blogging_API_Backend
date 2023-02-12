package com.codewithchhotu.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
		
	private int categoryId;
	@NotEmpty(message="pls write tittle")
	private String categoryTittle;
	@NotEmpty(message="pls give description")
	private String categoryDescription;
	

	
}
