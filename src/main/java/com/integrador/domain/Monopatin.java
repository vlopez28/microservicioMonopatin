package com.integrador.domain;

import com.integrador.service.dto.monopatin.MonopatinRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
public class Monopatin {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_monopatin")
    private Long id;

	@Embedded
    private GPS ubicacion;

    //esta en uso-disponible-enMantenimiento
    @Column
    private String estado;

    //va aumentando a cada viaje
    @Column
    private double kmsRecorridos;

    //va aumentando a cada viaje
    @Column
    private Long tiempoUsoTotal; //en segundos
    
    //iria un tiempo pausado???
    @Column
    private Long tiempoPausado; //en segundos
    
    @Column
    private Long cantidadViajes;

    public Monopatin(MonopatinRequestDto request) {
        this.ubicacion = request.getUbicacion();
        this.estado = request.getEstado();
        this.kmsRecorridos = request.getKmsRecorridos();
        this.tiempoUsoTotal = request.getTiempoUsoTotal();
        this.tiempoPausado = request.getTiempoPausado();
        this.cantidadViajes = request.getCantidadViajes();
    }
    
    

	public Monopatin() {
	
	}



	public void setId(Long id) {
		this.id = id;
	}



	public void setUbicacion(GPS ubicacion) {
		this.ubicacion = ubicacion;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public void setKmsRecorridos(double kmsRecorridos) {
		this.kmsRecorridos = kmsRecorridos;
	}



	public void setTiempoUsoTotal(Long tiempoUsoTotal) {
		this.tiempoUsoTotal = tiempoUsoTotal;
	}



	public void setTiempoPausado(Long tiempoPausado) {
		this.tiempoPausado = tiempoPausado;
	}



	public void setCantidadViajes(Long cantidadViajes) {
		this.cantidadViajes = cantidadViajes;
	}



	public Long getId() {
		return id;
	}
	
	public Long getTiempoPausado() {
		return tiempoPausado;
	}

	public Long getCantidadViajes() {
		return cantidadViajes;
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
