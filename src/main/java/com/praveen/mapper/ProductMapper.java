package com.praveen.mapper;

import com.praveen.dto.ProductDto;
import com.praveen.model.Product;

//@Component
public class ProductMapper {

	public Product toProduct(ProductDto productDto) {
		Product product = Product.builder().id(productDto.getId()).name(productDto.getName())
				.description(productDto.getDescription()).price(productDto.getPrice())
				.quantity(productDto.getQuantity()).build();
		return product;
	}

	public ProductDto toProductDto(Product product) {
		ProductDto productDto = ProductDto.builder().id(product.getId()).name(product.getName())
				.description(product.getDescription()).price(product.getPrice()).quantity(product.getQuantity())
				.build();
		return productDto;
	}

}
