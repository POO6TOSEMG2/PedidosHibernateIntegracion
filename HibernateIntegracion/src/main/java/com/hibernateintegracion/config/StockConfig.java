package com.hibernateintegracion.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import com.hibernateintegracion.controllers.StockController;

@Configuration
public class StockConfig {
	
	@Bean
	public StockController stockController()
	{
		StockController stockController = new StockController();
		return stockController;
	}
	
}
