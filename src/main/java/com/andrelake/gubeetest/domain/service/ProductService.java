package com.andrelake.gubeetest.domain.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrelake.gubeetest.domain.model.Product;
import com.andrelake.gubeetest.domain.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository prodRepository;
	
	public Iterable<Product> list() {
		return prodRepository.findAll();
	}
	
	public Product save(Product prod) {
		return prodRepository.save(prod);
	}
	
	public Iterable<Product> list(Set<Product> products) {
		
		return prodRepository.saveAll(products);
	}

}
