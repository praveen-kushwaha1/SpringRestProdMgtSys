package com.praveen.validator;

import org.apache.coyote.BadRequestException;

import com.praveen.dto.ProductDto;



public class ProductDTOValidator {
	
	public void ValidateProduct(ProductDto productDto) throws BadRequestException {
		
		if(productDto.getName()!=null&&productDto.getName().isEmpty()){
			throw new BadRequestException("Name is empty");
		}
		
		if(productDto.getDescription()!=null&&productDto.getDescription().isEmpty()){
			throw new BadRequestException("Description is empty");
		}
	}

}