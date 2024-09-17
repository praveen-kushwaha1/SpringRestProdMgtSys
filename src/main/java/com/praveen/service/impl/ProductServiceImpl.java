package com.praveen.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.praveen.dto.ProductDto;
import com.praveen.mapper.ProductMapper;
import com.praveen.model.Product;
import com.praveen.repository.ProductRepository;
import com.praveen.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public Boolean saveProduct(ProductDto productDto) {

//		Product product = new Product();
//		product.setId(productDto.getId());
//		product.setName(productDto.getName());
//		product.setDescription(productDto.getDescription());
//		product.setPrice(productDto.getPrice());
//		product.setQuantity(productDto.getQuantity());

		//Product product = mapper.map(productDto, Product.class);
		Product product1 = productMapper.toProduct(productDto);
		Product save = productRepository.save(product1);
		if (ObjectUtils.isEmpty(save)) // save=!nulll
		{
			return false;
		}
		return true;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> productList = productRepository.findAll();
		List<ProductDto> productDtoList = productList.stream().map(product -> productMapper.toProductDto(product))
				.collect(Collectors.toList());

		return productDtoList;
	}

	@Override
	public ProductDto getProductById(Integer id) {
		Optional<Product> findByProduct = productRepository.findById(id);
		if (findByProduct.isPresent()) {
			Product product = findByProduct.get();
			ProductDto productDto = productMapper.toProductDto(product);
			return productDto;
		}
		return null;
	}

	@Override
	public Boolean deleteProduct(Integer id) {
		Optional<Product> findByProduct = productRepository.findById(id);
		if (findByProduct.isPresent()) {
			Product product = findByProduct.get();
			productRepository.delete(product);
			return true;
		}
		return false;
	}



}