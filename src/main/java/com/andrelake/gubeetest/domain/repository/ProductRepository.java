package com.andrelake.gubeetest.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andrelake.gubeetest.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query(value = "SELECT x FROM Product x WHERE x.targetMarket in :targetMarket")
	List<Product> findByTargetMarketContainingIgnoreCase(@Param("targetMarket") String targetMarket);
	
}
