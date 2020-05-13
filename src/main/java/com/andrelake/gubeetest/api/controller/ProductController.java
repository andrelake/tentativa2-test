package com.andrelake.gubeetest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrelake.gubeetest.domain.model.Product;
import com.andrelake.gubeetest.domain.service.ProductService;

@RestController
@RequestMapping("/gubeeproducts")
public class ProductController {

	@Autowired
	private ProductService prodService;
	
	@GetMapping("/list")
	public Iterable<Product> list() {
		
		return prodService.list();
	}
}
