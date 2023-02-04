package com.ecarttry.ecommers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPageDto {

	private Integer size;
	private Long totalElements;
	private Integer totalPages;
	private Integer number;
}
