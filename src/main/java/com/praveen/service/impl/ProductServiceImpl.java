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
import com.praveen.dto.ProductResponse;
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

		// Product product = mapper.map(productDto, Product.class);
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

	@Override
	public ProductResponse getProductsWithPagination(int pageNo, int pageSize, String sortBy, String sortDir) {

//		Sort sort = Sort.by(sortBy).ascending();
//		Sort sort2 = Sort.by(sortBy).descending();

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Product> page = productRepository.findAll(pageable);

		List<Product> products = page.getContent();

		List<ProductDto> productsDtos = products.stream().map(prod -> productMapper.toProductDto(prod)).toList();
		long totalElements = page.getTotalElements();
		int totalPages = page.getTotalPages();
		boolean first = page.isFirst();
		boolean last = page.isLast();

//		ProductResponse productResponse=new ProductResponse();
//		productResponse.setProducts(productsDtos);
//		productResponse.setTotalElements(totalElements);

		ProductResponse productResponse = ProductResponse.builder().products(productsDtos).totalElements(totalElements)
				.totalPages(totalPages).isFirst(first).isLast(last).pageNo(pageNo).pageSize(pageSize).build();

		return productResponse;

	}

}