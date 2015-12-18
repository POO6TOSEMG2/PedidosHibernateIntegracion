package com.hibernateintegracion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hibernateintegracion.controllers.AlmacenesController;

@Configuration
public class AlmacenesConfig {

	@Bean
	public AlmacenesController almacenesController()
	{
		AlmacenesController almacenesController = new AlmacenesController();
		return almacenesController;
	}
	
}
