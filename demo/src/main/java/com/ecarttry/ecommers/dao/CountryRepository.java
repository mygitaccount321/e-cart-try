package com.ecarttry.ecommers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecarttry.ecommers.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
