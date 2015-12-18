package com.hibernateintegracion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hibernateintegracion.controllers.PedidosController;

@Configuration
public class PedidosConfig {
	
	@Bean
	public PedidosController pedidosController(){
		PedidosController pedidosController = new PedidosController();
		return pedidosController;
	}
	
}
