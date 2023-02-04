package com.ecarttry.ecommers.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPageDto {

	private List<ProductDto> productDto;
	private MyPageDto pageDto;
}
