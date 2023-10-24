package com.integrador.service.dto.parada;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.integrador.domain.GPS;

import jakarta.persistence.Column;
import lombok.Data;


@Data
@JsonIgnoreProperties( ignoreUnknown = true )
public class ParadaRequestDto {

	 private Long id;
	 private String nombre;
	 private GPS ubicacion;
	public Long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public GPS getUbicacion() {
		return ubicacion;
	}
	 
	 
}
