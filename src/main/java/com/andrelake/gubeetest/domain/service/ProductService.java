package com.andrelake.gubeetest.domain.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrelake.gubeetest.domain.exception.ProductNotFoundException;
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
	
	public List<Product> listByTargetMarketName(String market) {
		
		List<Product> lista = prodRepository.findByTargetMarketIgnoreCase(market);
		return lista;
	}
	
	public List<Product> listByStackName(String stack) {
		
		List<Product> lista = prodRepository.findByStackIgnoreCase(stack);
		return lista;
	}

	public Product findById(Long id) {
		
		Product prod = findOrFail(id);
		return prod;
	}
	
	public Product findOrFail(Long id) {
		
		Product prod = prodRepository.findById(id)
					.orElseThrow(() -> new ProductNotFoundException(id));
		return prod;
	}
	
	public void deleteById(Long id) {
		
		findOrFail(id);
		prodRepository.deleteById(id);
	}
}
