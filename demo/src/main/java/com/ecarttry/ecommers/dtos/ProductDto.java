package com.ecarttry.ecommers.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	private Long id;
	private ProductCategoryDto categoryDto;
	private String sku;
	private String name;
	private String description;
	private BigDecimal unitPrice;
	private String imageUrl;
	private boolean active;
	private int unitsInStock;
	private Date dateCreated;
	private Date lastUpdated;
}
