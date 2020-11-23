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
import com.axity.status.model.SmsModel;
import com.axity.status.service.model.ServicioModel;
import com.axity.status.util.TransactionCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Service
public class StatusService {
	   @Value("${url.app}")
	   private String hostapp;
	   @Value("${url.sms}")
	   private String hostsms;
	   @Value("${url.forms}")
	   private String hostforms;
	   @Value("${url.apdo}")
	   private String hostapdo;
	   @Value("${url.apdoudp}")
	   private String hostapdoudp;
	   @Value("${url.schedule}")
	   private String hostschedule;
	   @Value("${url.catalogo}")
	   private String hostcatalogo;
	private static final Set<HttpStatus> validStates = EnumSet.of(HttpStatus.OK, HttpStatus.CREATED, HttpStatus.ACCEPTED);
	private static final Set<HttpStatus> invalidStates = EnumSet.of(HttpStatus.UNAUTHORIZED, HttpStatus.NOT_FOUND);
	private static final Set<HttpStatus> maintenanceStates = EnumSet.of(HttpStatus.SERVICE_UNAVAILABLE);

	
	private static final Logger LOG = LoggerFactory.getLogger(StatusService.class);
	public Modelo status() {
		// TODO Auto-generated method stub
		Modelo response = new Modelo();
		try {
			List<ServicioModel> consulta = new ArrayList<>();
			consulta.add(consultaApp());
			consulta.add(consultaSMS());
			consulta.add(consultaForms());
			consulta.add(consultaApdo());
			consulta.add(consultaApdoUDP());
			consulta.add(consultaSchedule());
			consulta.add(consultaCatalogo());


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
		final String uri = hostapp;
	    RestTemplate restTemplate = new RestTemplate();
	 
	    AppModel newAppModel = new AppModel();
	    newAppModel.setEmail("jorge.delgado2@axity.com");
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    

	    Gson gson = new GsonBuilder().create();
	    String json = gson.toJson(newAppModel);
	    HttpEntity<String> request = new HttpEntity<>(json, headers);
	    
		service.setName("App"); 
	    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
	    if (validStates.contains(response.getStatusCode())) {
	    	service.setStatus("Activo");
	    	String respuestabody = response.getBody();
	    LOG.info("El contenido de la respuesta es "+respuestabody);
	    }else 
	    if (invalidStates.contains(response.getStatusCode())) {
	    	service.setStatus("Fuera de Servicio");
	    	String respuestabody = response.getBody();
	    LOG.info("El contenido de la respuesta es "+respuestabody);
	    }else 
		if (maintenanceStates.contains(response.getStatusCode())) {
		    	service.setStatus("Mantenimiento");
		    	String respuestabody = response.getBody();
		    LOG.info("El contenido de la respuesta es "+respuestabody);
		    }
	    return service;
	    //Use the response.getBody()
	}
	private ServicioModel consultaSMS()
	{
		ServicioModel serviciosms = new ServicioModel();
		final String uri = hostsms;
	    RestTemplate restTemplate = new RestTemplate();
	    
	    SmsModel newSmsModel = new SmsModel();
	    newSmsModel.setEmail("luis.buitrago@axity.com");
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    Gson gson = new GsonBuilder().create();
	    String json = gson.toJson(newSmsModel);
	    HttpEntity<String> request = new HttpEntity<>(json, headers);
	    
	    serviciosms.setName("SMS"); 
	    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
	    if (validStates.contains(response.getStatusCode())) {
	    	serviciosms.setStatus("Activo");
	    	String respuestabody = response.getBody();
	    LOG.info("El contenido de la respuesta es "+respuestabody);
	    }else 
		if (invalidStates.contains(response.getStatusCode())) {
		    	serviciosms.setStatus("Fuera de Servicio");
		    	String respuestabody = response.getBody();
		    LOG.info("El contenido de la respuesta es "+respuestabody);
		    }
		else 
	    if (maintenanceStates.contains(response.getStatusCode())) {
			    	serviciosms.setStatus("Mantenimiento");
			    	String respuestabody = response.getBody();
			    LOG.info("El contenido de la respuesta es "+respuestabody);
			    }
	    
		return serviciosms;
	}
	private ServicioModel consultaForms()
	{
		ServicioModel servicioforms = new ServicioModel();
		final String uri = hostforms;
	    RestTemplate restTemplate = new RestTemplate();
	       
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	   
	    HttpEntity<String> request = new HttpEntity<>(null, headers);
	    
	    servicioforms.setName("Forms"); 
	    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
	    if (validStates.contains(response.getStatusCode())) {
	    	servicioforms.setStatus("Activo");
	    	String respuestabody = response.getBody();
	    LOG.info("El contenido de la respuesta es "+respuestabody);
	    }else 
		if (invalidStates.contains(response.getStatusCode())) {
		    	servicioforms.setStatus("Fuera de Servicio");
		    	String respuestabody = response.getBody();
		    LOG.info("El contenido de la respuesta es "+respuestabody);
		    }
		else 
	    if (maintenanceStates.contains(response.getStatusCode())) {
			    	servicioforms.setStatus("Mantenimiento");
			    	String respuestabody = response.getBody();
			    LOG.info("El contenido de la respuesta es "+respuestabody);
			    }
	    
		return servicioforms;
	}
	private ServicioModel consultaApdo()
	{
		ServicioModel servicioapdo = new ServicioModel();
		final String uri = hostapdo;
	    RestTemplate restTemplate = new RestTemplate();
	    
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	   
	    HttpEntity<String> request = new HttpEntity<>(null, headers);
	    
	    servicioapdo.setName("Apartado de Lugares"); 
	    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
	    if (validStates.contains(response.getStatusCode())) {
	    	servicioapdo.setStatus("Activo");
	    	String respuestabody = response.getBody();
	    LOG.info("El contenido de la respuesta es "+respuestabody);
	    }else 
		if (invalidStates.contains(response.getStatusCode())) {
			servicioapdo.setStatus("Fuera de Servicio");
		    	String respuestabody = response.getBody();
		    LOG.info("El contenido de la respuesta es "+respuestabody);
		    }
		else 
	    if (maintenanceStates.contains(response.getStatusCode())) {
	    	servicioapdo.setStatus("Mantenimiento");
			    	String respuestabody = response.getBody();
			    LOG.info("El contenido de la respuesta es "+respuestabody);
			    }
	    
		return servicioapdo;
	}
	private ServicioModel consultaApdoUDP()
	{
		ServicioModel servicioapdoudp = new ServicioModel();
		final String uri = hostapdoudp;
	    RestTemplate restTemplate = new RestTemplate();
	    
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	   
	    HttpEntity<String> request = new HttpEntity<>(null, headers);
	    
	    servicioapdoudp.setName("Apartado de Lugares UDP"); 
	    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
	    if (validStates.contains(response.getStatusCode())) {
	    	servicioapdoudp.setStatus("Activo");
	    	String respuestabody = response.getBody();
	    LOG.info("El contenido de la respuesta es "+respuestabody);
	    }else 
		if (invalidStates.contains(response.getStatusCode())) {
			servicioapdoudp.setStatus("Fuera de Servicio");
		    	String respuestabody = response.getBody();
		    LOG.info("El contenido de la respuesta es "+respuestabody);
		    }
		else 
	    if (maintenanceStates.contains(response.getStatusCode())) {
	    	servicioapdoudp.setStatus("Mantenimiento");
			    	String respuestabody = response.getBody();
			    LOG.info("El contenido de la respuesta es "+respuestabody);
			    }
	    
		return servicioapdoudp;
	}
	private ServicioModel consultaSchedule()
	{
		ServicioModel servicioschedule = new ServicioModel();
		final String uri = hostschedule;
	    RestTemplate restTemplate = new RestTemplate();
	    
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	   
	    HttpEntity<String> request = new HttpEntity<>(null, headers);
	    
	    servicioschedule.setName("Schedule"); 
	    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
	    if (validStates.contains(response.getStatusCode())) {
	    	servicioschedule.setStatus("Activo");
	    	String respuestabody = response.getBody();
	    LOG.info("El contenido de la respuesta es "+respuestabody);
	    }else 
		if (invalidStates.contains(response.getStatusCode())) {
			servicioschedule.setStatus("Fuera de Servicio");
		    	String respuestabody = response.getBody();
		    LOG.info("El contenido de la respuesta es "+respuestabody);
		    }
		else 
	    if (maintenanceStates.contains(response.getStatusCode())) {
	    	servicioschedule.setStatus("Mantenimiento");
			    	String respuestabody = response.getBody();
			    LOG.info("El contenido de la respuesta es "+respuestabody);
			    }
	    
		return servicioschedule;
	}
	private ServicioModel consultaCatalogo()
	{
		ServicioModel serviciocatalogo = new ServicioModel();
		final String uri = hostcatalogo;
	    RestTemplate restTemplate = new RestTemplate();
	    
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	   
	    HttpEntity<String> request = new HttpEntity<>(null, headers);
	    
	    serviciocatalogo.setName("Catalogo Ladas"); 
	    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
	    if (validStates.contains(response.getStatusCode())) {
	    	serviciocatalogo.setStatus("Activo");
	    	String respuestabody = response.getBody();
	    LOG.info("El contenido de la respuesta es "+respuestabody);
	    }else 
		if (invalidStates.contains(response.getStatusCode())) {
			serviciocatalogo.setStatus("Fuera de Servicio");
		    	String respuestabody = response.getBody();
		    LOG.info("El contenido de la respuesta es "+respuestabody);
		    }
		else 
	    if (maintenanceStates.contains(response.getStatusCode())) {
	    	serviciocatalogo.setStatus("Mantenimiento");
			    	String respuestabody = response.getBody();
			    LOG.info("El contenido de la respuesta es "+respuestabody);
			    }
	    
		return serviciocatalogo;
	}

}
