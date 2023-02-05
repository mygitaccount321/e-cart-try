package com.ecarttry.ecommers.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecarttry.ecommers.dao.CountryRepository;
import com.ecarttry.ecommers.dao.ProductCategoryRepository;
import com.ecarttry.ecommers.dao.ProductRepository;
import com.ecarttry.ecommers.dao.StateRepository;
import com.ecarttry.ecommers.dtos.CountryDto;
import com.ecarttry.ecommers.dtos.ProductCategoryDto;
import com.ecarttry.ecommers.dtos.ProductDto;
import com.ecarttry.ecommers.dtos.ProductPageDto;
import com.ecarttry.ecommers.dtos.StateDto;
import com.ecarttry.ecommers.mapper.ProductDtoMapper;

@RestController
public class EcartController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private ProductDtoMapper productDtoMapper;

	@GetMapping("/test")
	public String getTest() {
		return "test123";
	}

	@GetMapping("/api/products")
	public List<ProductDto> getProduct() {
		return productDtoMapper.toProductDto(productRepository.findAll());
	}

	@GetMapping("/api/product")
	public ProductDto getProductById(@PathParam("id") Long id) {
		return productDtoMapper.toProductDtoOne(productRepository.findById(id).orElse(null));
	}
	@GetMapping("/api/products/search/findByCategoryId")
	public List<ProductDto> getFindByCategory(@Param("id") Long id) {
		System.out.println(id);
		return productDtoMapper.toProductDto(productRepository.findByCategoryId(id));
	}
	
	@GetMapping("/api/product-category")
	public List<ProductCategoryDto> getProductCategory(@Param("id") Long id) {
		System.out.println(id);
		return productDtoMapper.toProductCatDto(productCategoryRepository.findAll());
	}
	
	@GetMapping("/api/products/search/findByNameContaining")
	public List<ProductDto> getFindByNameContaining(@Param("name") String name) {
		return productDtoMapper.toProductDto(productRepository.findByNameContaining(name));
	}
	
	@GetMapping("/api/products/search/findByCategoryIdAndPage")
	public ProductPageDto getFindByCategory(@Param("id") Long id, @Param("page") Integer page, @Param("size") Integer size) {
		System.out.println(id);
		Pageable pageble= PageRequest.of(page,size);
		return productDtoMapper.toPageProductDto(productRepository.findByCategoryId(id, pageble));
	}
	
	@GetMapping("/api/products/search/findByNameContainingAndPage")
	public ProductPageDto getFindByNameContaining(@Param("name") String name,  @Param("page") Integer page, @Param("size") Integer size) {
		Pageable pageble= PageRequest.of(page,size);
		return productDtoMapper.toPageProductDto(productRepository.findByNameContaining(name, pageble));
	}
	
	@GetMapping("/api/countries")
	public List<CountryDto> getCountries() {
		return productDtoMapper.toCountryDto(countryRepository.findAll());
	}
	
	@GetMapping("/api/states/search/findByCountryCode")
	public List<StateDto> getFindByCountryCode(@Param("code") String code) {
		return productDtoMapper.toStateDto(stateRepository.findByCountryCode(code));
	}

}
