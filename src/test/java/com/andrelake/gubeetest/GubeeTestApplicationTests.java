package com.andrelake.gubeetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import com.andrelake.gubeetest.domain.exception.ProductNotFoundException;
import com.andrelake.gubeetest.domain.model.Product;
import com.andrelake.gubeetest.domain.service.ProductService;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@TestPropertySource("/application-test.properties")
class GubeeTestApplicationTests {

	private static final String NAME_PRODUCT_TEST = "Gubee BankApp";

	@LocalServerPort
	private int port;
	
	@Autowired
	private ProductService service;
	
	@BeforeEach
	public void setUp() {
		
		RestAssured.port = port;
		RestAssured.basePath = "/gubeeproducts";
	}
	
	@Test
	public void shouldPersistProduct_WhenUseSave() {
		
		Product newProduct = createProduct();
		service.save(newProduct);
		
		assertThat(newProduct.getId()).isNotNull();
		assertThat(newProduct.getProductName()).isEqualTo(NAME_PRODUCT_TEST);
	}
	
	@Test
	public void shouldDeleteProduct_WhenUseDelete() {
		
		Product newProduct = createProduct();
		service.save(newProduct);
		
		service.deleteById(newProduct.getId());
		
		assertThrows(ProductNotFoundException.class, () -> {
			service.findById(newProduct.getId());
		});
	}
	
	@Test
	public void shouldUpdateProductName_WhenUseSaveAfterChangeProperties() {
		
		Product newProduct = createProduct();
		service.save(newProduct);
		
		newProduct.setProductName("Outro Gubee Bank");
		service.save(newProduct);
		
		assertThat(newProduct.getProductName()).isEqualTo("Outro Gubee Bank");
	}
	
	public Product createProduct() {
		
		List<String> stack = List.of("Spring", "NodeJS", "Java 11");

		List<String> targetMarket = List.of("Banco", "Banco Digital"); 
		
		Product prod = new Product();
		prod.setProductName(NAME_PRODUCT_TEST);
		prod.setDescription("Ferramenta de gestão de bancos");
		prod.setStack(stack);
		prod.setTargetMarket(targetMarket);
		
		return prod;
	}
}
