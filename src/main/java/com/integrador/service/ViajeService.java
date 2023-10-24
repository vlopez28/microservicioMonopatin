package com.integrador.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.integrador.domain.Viaje;
import com.integrador.repository.ViajeRepository;
import com.integrador.service.dto.viaje.*;
import com.integrador.service.exception.*;

import jakarta.transaction.Transactional;

@Service
public class ViajeService {
	
private ViajeRepository viajeRepository;
	
	public ViajeService(ViajeRepository viajeRepository) {
		this.viajeRepository = viajeRepository;
		
	}
	
	
	
	 @Transactional
   public ViajeResponseDto save(ViajeRequestDto request ){
       Viaje viaje = new Viaje(request);
       Viaje result = this.viajeRepository.save(viaje);
       return new ViajeResponseDto(result);
   }
	    
	    
 

   @Transactional
   public List<ViajeResponseDto> findAll(){
       return this.viajeRepository.findAll().stream().map( ViajeResponseDto::new ).toList();
   }

   @Transactional
   public ViajeResponseDto findById( Long id ){
       return this.viajeRepository.findById( id )
               .map( ViajeResponseDto::new )
               .orElseThrow( () -> new NotFoundException("Viaje", id ) );
   }
   
   @Transactional
   public void delete(Long id) {
   	this.viajeRepository.delete(this.viajeRepository.findById(id).orElseThrow(
               () -> new NotFoundException("ID de usuario invalido:" + id)));
   }
   

   @Transactional
   public Viaje update(Long id, ViajeRequestDto request) {
       Viaje viaje = this.viajeRepository.findById(id).orElseThrow(
               () -> new NotFoundException("ID de viaje inválido: " + id));
       
       viaje.setCosto(request.getCosto());
       viaje.setCuentaId(request.getCuentaId());
       viaje.setFinViaje(request.getFinViaje());
       viaje.setInicioViaje(request.getInicioViaje());
       viaje.setKmsRecorridos(request.getKmsRecorridos());
       viaje.setMonopatin(request.getMonopatin());
       viaje.setParadaFinal(request.getParadaFinal());
       viaje.setPausaActiva(request.isPausaActiva());
       viaje.setTiempoPausa(request.getTiempoPausa());
       viaje.setUsuarioId(request.getUsuarioId());
       return this.viajeRepository.save(viaje);
   }

}
