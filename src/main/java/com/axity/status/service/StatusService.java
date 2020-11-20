package com.axity.status.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.axity.status.model.AppModel;
import com.axity.status.model.Modelo;
import com.axity.status.service.model.ServicioModel;
import com.axity.status.util.TransactionCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Service
public class StatusService {
	   @Value("${url.app}")
	   private String host;
	private static final Set<HttpStatus> validStates = EnumSet.of(HttpStatus.OK, HttpStatus.CREATED, HttpStatus.ACCEPTED);

	private static final Logger LOG = LoggerFactory.getLogger(StatusService.class);
	public Modelo status() {
		// TODO Auto-generated method stub
		Modelo response = new Modelo();
		try {
			List<ServicioModel> consulta = new ArrayList<>();
			consulta.add(consultaApp());
			response.setServicios(consulta);
			response.setCode(TransactionCode.OK.getCode());
			response.setMessage(TransactionCode.OK.getMessage());
		} catch(Exception e) {
			response.setCode(TransactionCode.ERROR_TRANSACTION.getCode());
			response.setMessage(TransactionCode.ERROR_TRANSACTION.getMessage());
			LOG.error("Error al obtener el catalogo de establecimientos");
			e.printStackTrace();
		}
		
		return response;
	}
	private  ServicioModel consultaApp()
	{
	    
		ServicioModel service = new ServicioModel();
		final String uri = host;
	    RestTemplate restTemplate = new RestTemplate();
	 
	    AppModel newAppModel = new AppModel();
	    newAppModel.setEmail("jorge.delgado2@axity.com");
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    

	    Gson gson = new GsonBuilder().create();
	    String json = gson.toJson(newAppModel);
	    HttpEntity<String> request = new HttpEntity<>(json, headers);
	    
		service.setName("app"); 
	    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
	    if (validStates.contains(response.getStatusCode())) {
	    	service.setStatus("Activo");
	    	String respuestabody = response.getBody();
	    LOG.info("El contenido de la respuesta es "+respuestabody);
	    }
	    return service;
	    //Use the response.getBody()
	}

}
