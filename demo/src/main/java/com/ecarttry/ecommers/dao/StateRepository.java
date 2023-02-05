package com.ecarttry.ecommers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecarttry.ecommers.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

	List<State> findByCountryCode(String code);


}
