package com.integrador.service.dto.monopatin;

import java.time.Duration;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.integrador.domain.GPS;
import com.integrador.domain.Viaje;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@JsonIgnoreProperties( ignoreUnknown = true )
public class MonopatinRequestDto {
	private Long idMonopatin;
    private GPS ubicacion;
    private String estado;
    private boolean disponible;
    private double kmsRecorridos;
    private double kmsMantenimiento;
    private double tiempoUsoParaMant;
    private Long tiempoUsoTotal;
    private Long tiempoPausado;
    private Long cantidadViajes;
    
    
    

	public double getTiempoUsoParaMant() {
		return tiempoUsoParaMant;
	}


	public double getKmsMantenimiento() {
		return kmsMantenimiento;
	}


	public Long getCantidadViajes() {
		return cantidadViajes;
	}
    
    
    public boolean isDisponible() {
		return disponible;
	}


	public Long getTiempoPausado() {
		return tiempoPausado;
	}
	
    public Long getIdMonopatin() {
		return idMonopatin;
	}
	
	public GPS getUbicacion() {
		return ubicacion;
	}
	public String getEstado() {
		return estado;
	}
	public double getKmsRecorridos() {
		return kmsRecorridos;
	}
	public Long getTiempoUsoTotal() {
		return tiempoUsoTotal;
	}
    
    
}
