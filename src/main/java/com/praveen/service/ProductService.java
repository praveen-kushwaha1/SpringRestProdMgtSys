package com.praveen.service;

import java.util.List;

import com.praveen.dto.ProductDto;

import com.praveen.model.Product;

public interface ProductService {

	public Boolean saveProduct(ProductDto productDto);

	public List<ProductDto> getAllProducts();

	public ProductDto getProductById(Integer id);

	public Boolean deleteProduct(Integer id);



}