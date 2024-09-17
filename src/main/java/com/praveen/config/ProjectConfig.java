package com.praveen.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.praveen.mapper.ProductMapper;
import com.praveen.validator.ProductDTOValidator;

@Configuration
public class ProjectConfig {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

	@Bean
	public ProductMapper productMapper() {
		return new ProductMapper();
	}
	@Bean
	public ProductDTOValidator productDTOValidator() {
		return new ProductDTOValidator();
	}

}