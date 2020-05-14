package com.andrelake.gubeetest.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.andrelake.gubeetest.domain.model.Product;
import com.andrelake.gubeetest.domain.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/gubeeproducts")
public class ProductController {

	@Autowired
	private ProductService prodService;
	
	@GetMapping("/list")
	public ResponseEntity<Iterable<Product>> list() {
		
		Iterable<Product> list = prodService.list();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		
		Product prod = prodService.findById(id);
		return ResponseEntity.ok(prod);
	}
	
	@GetMapping("/pormarket")
	public ResponseEntity<List<Product>> listByTargetMarket(@RequestParam String text) {
		
		List<Product> lista = prodService.listByTargetMarketName(text);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/porstack")
	public ResponseEntity<List<Product>> listByStack(@RequestParam String text) {
		
		List<Product> lista = prodService.listByStackName(text);
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody @Valid Product product) {
		
		product = prodService.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody @Valid Product product) {
		
		Product oldProduct = prodService.findById(id);
		
		BeanUtils.copyProperties(product, oldProduct, "id");
		
		oldProduct = prodService.save(oldProduct);
		return ResponseEntity.ok(oldProduct);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody @Valid Map<String, Object> fields, HttpServletRequest request) {
		
		Product actualProduct = prodService.findById(id);
		
		merge(fields, actualProduct, request);
		
		return update(id, actualProduct);
	}

	private void merge(Map<String, Object> sourceData, Product targetProduct, HttpServletRequest request) {
		
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			Product sourceProduct = mapper.convertValue(sourceData, Product.class);
			sourceData.forEach((propName, propValue) -> {
				
				Field field = ReflectionUtils.findField(Product.class, propName);
				field.setAccessible(true);
				
				Object newValue = ReflectionUtils.getField(field, sourceProduct);
				
				ReflectionUtils.setField(field, targetProduct, newValue);
			});
		}
		catch(IllegalArgumentException e) {
			throw new HttpMessageNotReadableException(e.getMessage(), serverHttpRequest);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		
		prodService.deleteById(id);
	}
}
