package com.integrador.service.dto.viaje;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.integrador.domain.Monopatin;
import com.integrador.domain.Parada;
import com.integrador.domain.clases.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@JsonIgnoreProperties( ignoreUnknown = true )
public class ViajeRequestDto {
	
    private Long id;
    private Date inicioViaje;
    private Date finViaje;
    private double costo;
    private Monopatin monopatin;
    private Long cuentaId;
    private Long usuarioId;
    private Parada paradaFinal;
    private double kmsRecorridos;
    private Long tiempoPausa; //en segundos
    private boolean pausaActiva;
    
    public double getKmsRecorridos() {
		return kmsRecorridos;
	}
	public Long getTiempoPausa() {
		return tiempoPausa;
	}
	public boolean isPausaActiva() {
		return pausaActiva;
	} 
    
	public Long getId() {
		return id;
	}
	public Date getInicioViaje() {
		return inicioViaje;
	}
	public Date getFinViaje() {
		return finViaje;
	}
	public double getCosto() {
		return costo;
	}
	public Monopatin getMonopatin() {
		return monopatin;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public Long getCuentaId() {
		return cuentaId;
	}
	public Parada getParadaFinal() {
		return paradaFinal;
	}
   

    
    

}