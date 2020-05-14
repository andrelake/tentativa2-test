package com.andrelake.gubeetest.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andrelake.gubeetest.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("SELECT x FROM Product x INNER JOIN x.targetMarket t WHERE t = :targetMarket")
	List<Product> findByTargetMarketIgnoreCase(@Param("targetMarket") String market);
	
	@Query("SELECT x FROM Product x INNER JOIN x.stack s WHERE s = :stack")
	List<Product> findByStackIgnoreCase(@Param("stack") String stack);
	
}
