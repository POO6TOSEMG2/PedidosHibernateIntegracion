package com.hibernateintegracion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hibernateintegracion.controllers.LineaPedidoController;

@Configuration
public class LineaPedidosConfig {
	
	@Bean
	public LineaPedidoController lineaPedidoController(){
		LineaPedidoController lineaPedidoController = new LineaPedidoController();
		return lineaPedidoController;
	}
	
}
