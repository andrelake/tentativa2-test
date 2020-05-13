package com.andrelake.gubeetest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.andrelake.gubeetest.domain.model.Product;
import com.andrelake.gubeetest.domain.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class GubeeTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GubeeTestApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductService prodService) {
		
		return args-> {
			
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/gubee-json.json");
			
			try {
				List<Product> products = mapper.readValue(inputStream, typeReference);
				prodService.list(products);
				System.out.println("Products saved!");
				
			}
			catch(IOException e) {
				System.out.println("Unable to save products: " + e.getMessage());
			}
		};
	}
}
