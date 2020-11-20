package com.axity.status.util;

public enum TransactionCode {
	OK                  (0, "Procesado correctamente"),
	ERROR_MESSAGE       (1, "Los valores recibidos del mensaje son incorrectos o incompletos"),
	ERROR_TRANSACTION   (2, "Error interno del servicio"),
	ERROR_DUPLICATE     (3, "Usuario registrado anteriormente"),
	ERROR_NO_DATA       (4, "Sin registros previos"),
	ERROR_NO_ACCESS		(5, "Error de acceso"),
	ERROR_INVALID_USER  (6, "Usuario inexistente en la base de datos"),
	ERROR_INVALID_KEY   (7, "Identificador a modificar invalido"),
	ERROR_TIMELIMIT_REACHED (8, "El código de verificación ha expirado"),
	ERROR_ATTEMPTS_LIMIT (9, "Se alcanzo él limite de intentos para ingresar el código de acceso"),
	ERROR_WRONG_EMAIL_PASSWORD (10, "Código de verificación no valido"),
	ERROR_INVALID_DOMAIN (11, "El dominio del email no es admitido para la organización"),
	ERROR_NO_FORMS      (12, "No hay formularios registrados"),
	ERROR_INVALID_ENTERPRISE (13, "La organización no se encuentra registrada"),
	ERROR_NO_SCHEDULE (14, "Servicio no contratado"),
	EXISTING_MEDICAL_REPORT (15, "Reporte medico realizado anteriormente"),
	UNEXISTING_MEDICAL_REPORT(16, "No existe un reporte medico previo"),
	EXISTING_DAILY_REPORT (17, "Reporte diario realizado anteriormente"),
	UNEXISTING_DAILY_REPORT (18, "No se ha realizado el reporte diario en el día en curso"),
	EXISTING_FORM (19, "Se registro con anterioridad un formulario para esta organización"),
	UNEXISTING_FORM (20, "El formulario que se intenta actualizar no existe"),
	UNAVAILABLE_ROOM (24, "La sala que intenta reservar ya esta ocupada."),
	UNEXISTING_EVENT (25, "El evento no existe."),
	UNAUTHORIZED_RESERVE(26, "El usuario no tiene permitido reservar un lugar"),
	EXISTING_ESTABLISHMENT(33, "El establecimiento ya ha sido registrado"),
	UNEXISTING_ESTABLISHMENT(34, "El establecimiento no existe"),
	EXISTING_RESERVATION(35, "Ya has realizado una reserva anteriormente en esta fecha."),
	UNAVAILABLE_DATE(36, "No se puede hacer una reserva para días anteriores a la fecha actual"),
	UNAVAILABLE_PLACE(37, "El establecimiento ya no tiene lugares disponibles"),
	UNEXISTING_RESERVATION(38, "La reserva no existe"),
	UNAVAILABLE_PLACE2(39, "El establecimiento no esta disponible");
	
	private Integer code;
	private String message;
	
	private TransactionCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}