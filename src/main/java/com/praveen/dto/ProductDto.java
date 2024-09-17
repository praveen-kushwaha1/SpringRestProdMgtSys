package com.praveen.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
	
	private Integer id;

	private String name;

	private String description;

	private Double price;

	private Integer quantity;
}