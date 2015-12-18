package com.hibernateintegracion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hibernateintegracion.controllers.ClienteController;

@Configuration
public class ClientesConfig {
	
	@Bean
	public ClienteController clienteController(){
		ClienteController clienteController = new ClienteController();
		return clienteController;
	}
	
}
