package com.hibernateintegracion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hibernateintegracion.controllers.ProductoController;

@Configuration
public class ProductosConfig {

	@Bean
	public ProductoController productoController(){
		ProductoController productoController = new ProductoController();
		return productoController;
	}
	
}
