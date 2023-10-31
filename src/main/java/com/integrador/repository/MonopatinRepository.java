package com.integrador.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.integrador.domain.Monopatin;
import com.integrador.service.dto.monopatin.MonopatinResponseDto;
import com.integrador.service.dto.monopatin.MonopatinesCantidadResponseDto;
import com.integrador.service.dto.monopatinConViajes.MonopatinConViajesResponseDto;

	@Repository
	public interface MonopatinRepository extends JpaRepository<Monopatin, Long>{
		
		
		@Query("SELECT m FROM Monopatin m WHERE m.kmsRecorridos >= :cantKm ORDER BY m.kmsRecorridos DESC")
		public List<MonopatinResponseDto> getMonopatinesPorKm(Long cantKm);
		
		@Query("SELECT m FROM Monopatin m WHERE m.tiempoUsoTotal >= :cantTiempo ORDER BY m.tiempoUsoTotal DESC")
		public List<MonopatinResponseDto> getMonopatinesPorTiempoSinPausa(Long cantTiempo);
		
		
		@Query("SELECT m FROM Monopatin m GROUP BY m.id HAVING SUM(m.tiempoUsoTotal + m.tiempoPausado) >= :cantTiempo ORDER BY m.kmsRecorridos DESC")
		public List<MonopatinResponseDto> getMonopatinesPorTiempoConPausa(Long cantTiempo);
		
		
		@Query("SELECT new com.integrador.service.dto.monopatinConViajes.MonopatinConViajesResponseDto(m.id, m.ubicacion, m.estado, m.disponible, m.kmsRecorridos, m.kmsMant, m.cantidadViajes, v.finViaje) FROM Viaje v JOIN Monopatin m ON v.monopatin.id = m.id WHERE EXTRACT(YEAR FROM v.finViaje) = :anio AND m.cantidadViajes > :cantViajes ORDER BY m.cantidadViajes ASC")	
		public List<MonopatinConViajesResponseDto> getMonopatinesConViajes(Long cantViajes, Integer anio);

		@Query("SELECT new com.integrador.service.dto.monopatin.MonopatinesCantidadResponseDto(COUNT(m.disponible), COUNT(m.estado = 'en mantenimiento')) FROM Monopatin m")
	    public List<MonopatinesCantidadResponseDto> getMonopatinesEnOperacionMantenimiento();

		
	}
	
	