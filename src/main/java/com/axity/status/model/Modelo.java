package com.axity.status.model;

import java.util.List;

import com.axity.status.service.model.ServicioModel;

public class Modelo {

	private int code;
	private String message;
	private List<ServicioModel> servicios;
	public int getCode() {
		return code;
	}
	public List<ServicioModel> getServicios() {
		return servicios;
	}
	public void setServicios(List<ServicioModel> servicios) {
		this.servicios = servicios;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
