package com.ecarttry.ecommers.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecarttry.ecommers.entity.Product;
public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findByCategoryId(Long id, Pageable pageble);

	List<Product> findByNameContaining(String name);

	List<Product> findByCategoryId(Long id);

	Page<Product> findByNameContaining(String name, Pageable pageble);


}
