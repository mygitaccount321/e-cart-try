package com.ecarttry.ecommers.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.ecarttry.ecommers.dtos.MyPageDto;
import com.ecarttry.ecommers.dtos.ProductCategoryDto;
import com.ecarttry.ecommers.dtos.ProductDto;
import com.ecarttry.ecommers.dtos.ProductPageDto;
import com.ecarttry.ecommers.entity.Product;
import com.ecarttry.ecommers.entity.ProductCategory;

@Component
public class ProductDtoMapper {

	public List<ProductDto> toProductDto(List<Product> products) {
		return products.stream().map((product) -> {
			 ProductDto productDto = new ProductDto();
			 productDto.setId(product.getId());
			 productDto.setSku(product.getSku());
			 productDto.setName(product.getName());
			 productDto.setDescription(product.getDescription());
			 productDto.setUnitPrice(product.getUnitPrice());
			 productDto.setImageUrl(product.getImageUrl());
			 productDto.setActive(product.isActive());
			 productDto.setUnitsInStock(product.getUnitsInStock());
			 productDto.setDateCreated(product.getDateCreated());
			 productDto.setLastUpdated(product.getLastUpdated());
			 
			 ProductCategoryDto productCategoryDto = new ProductCategoryDto();
			 productCategoryDto.setCategoryName(product.getCategory().getCategoryName());
			 productCategoryDto.setId(product.getCategory().getId());
			 productDto.setCategoryDto(productCategoryDto);
			 return productDto;
		}).collect(Collectors.toList());
	}
	
	public ProductPageDto toPageProductDto(Page<Product> products) {
		List<ProductDto> productDtos = products.getContent().stream().map((product) -> {
			 ProductDto productDto = new ProductDto();
			 productDto.setId(product.getId());
			 productDto.setSku(product.getSku());
			 productDto.setName(product.getName());
			 productDto.setDescription(product.getDescription());
			 productDto.setUnitPrice(product.getUnitPrice());
			 productDto.setImageUrl(product.getImageUrl());
			 productDto.setActive(product.isActive());
			 productDto.setUnitsInStock(product.getUnitsInStock());
			 productDto.setDateCreated(product.getDateCreated());
			 productDto.setLastUpdated(product.getLastUpdated());
			 ProductCategoryDto productCategoryDto = new ProductCategoryDto();
			 productCategoryDto.setCategoryName(product.getCategory().getCategoryName());
			 productCategoryDto.setId(product.getCategory().getId());
			 productDto.setCategoryDto(productCategoryDto);
			 return productDto;
		}).collect(Collectors.toList());
		MyPageDto myPageDto = new MyPageDto();
		myPageDto.setNumber(products.getNumber());
		myPageDto.setSize(products.getNumberOfElements());
		myPageDto.setTotalElements(products.getTotalElements());
		myPageDto.setTotalPages(products.getTotalPages());
		ProductPageDto productPageDto = new ProductPageDto();
		productPageDto.setProductDto(productDtos);
		productPageDto.setPageDto(myPageDto);
		return productPageDto;
	}
	
	public List<ProductCategoryDto> toProductCatDto(List<ProductCategory> productCategorys) {
		return productCategorys.stream().map((productCategory) -> {
			 ProductCategoryDto productCategoryDto = new ProductCategoryDto();
			 productCategoryDto.setCategoryName(productCategory.getCategoryName());
			 productCategoryDto.setId(productCategory.getId());
			 return productCategoryDto;
		}).collect(Collectors.toList());
	}
	
	public ProductDto toProductDtoOne(Product product) {
		 ProductDto productDto = new ProductDto();
		 productDto.setId(product.getId());
		 productDto.setSku(product.getSku());
		 productDto.setName(product.getName());
		 productDto.setDescription(product.getDescription());
		 productDto.setUnitPrice(product.getUnitPrice());
		 productDto.setImageUrl(product.getImageUrl());
		 productDto.setActive(product.isActive());
		 productDto.setUnitsInStock(product.getUnitsInStock());
		 productDto.setDateCreated(product.getDateCreated());
		 productDto.setLastUpdated(product.getLastUpdated());
		 
		 ProductCategoryDto productCategoryDto = new ProductCategoryDto();
		 productCategoryDto.setCategoryName(product.getCategory().getCategoryName());
		 productCategoryDto.setId(product.getCategory().getId());
		 productDto.setCategoryDto(productCategoryDto);
		 return productDto;
	}
}
