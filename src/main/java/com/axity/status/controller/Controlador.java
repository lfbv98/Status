package com.axity.status.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axity.status.model.Modelo;
import com.axity.status.service.StatusService;

@RestController
@RequestMapping(
		path = "/"
		)

public class Controlador {
	
	@Autowired
	StatusService estatus;
	
	
	@GetMapping(path = "/status/service")
	public Modelo getCatEstablecimientos()
			 {
		
					return estatus.status();
	}
}