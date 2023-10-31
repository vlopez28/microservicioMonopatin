package com.integrador.service.dto.monopatin;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MonopatinesCantidadResponseDto {
	
	private final Long cantidadMonopatinesOperacion;
	private final Long cantidadMonopatinesMantenimiento;
	
	public MonopatinesCantidadResponseDto(Long cantidadMonopatinesOperacion, Long cantidadMonopatinesMantenimiento) {
		this.cantidadMonopatinesOperacion = cantidadMonopatinesOperacion;
		this.cantidadMonopatinesMantenimiento = cantidadMonopatinesMantenimiento;
	}
	
	

}
