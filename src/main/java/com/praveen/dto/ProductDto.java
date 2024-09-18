package com.praveen.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import lombok.Data;

@Data
@Builder
public class ProductDto {
	
	
	private Integer id;
	//@NotBlank
	private String name;
	@NotEmpty
	@Size(min=3, max=10, message="Description min and max is 3-10")
	private String description;

	
	private Double price;

	private Integer quantity;
}