package com.integrador.service;

import java.net.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.integrador.domain.Parada;
import com.integrador.domain.Viaje;
import com.integrador.domain.clases.Usuario;
import com.integrador.repository.MonopatinRepository;
import com.integrador.repository.ParadaRepository;
import com.integrador.repository.ViajeRepository;
import com.integrador.service.dto.viaje.*;
import com.integrador.service.exception.*;

import jakarta.transaction.Transactional;

@Service
public class ViajeService {
	
	@Autowired
	private final RestTemplate restTemplate;
	
	private ViajeRepository viajeRepository;
	private MonopatinRepository monopatinRepository;
	private ParadaRepository paradaRepository;
	
	public ViajeService(ViajeRepository viajeRepository, MonopatinRepository monopatinRepository, ParadaRepository paradaRepository, RestTemplate restTemplate) {
		this.viajeRepository = viajeRepository;
		this.monopatinRepository = monopatinRepository;
		this.paradaRepository = paradaRepository;
		this.restTemplate = restTemplate;
		
	}
	
	public ResponseEntity conectarUsuario(Long idUsuario) {
		
		//HttpHeaders headers = new HttpHeaders();
		HttpEntity<	Usuario> requestEntity = new HttpEntity<>(null);
		
		ResponseEntity<Usuario> response = restTemplate.exchange(
				"http://localhost:8080/api/usuarios/" + idUsuario,
				HttpMethod.GET,
				requestEntity,
				
				new ParameterizedTypeReference<Usuario>() {}
				);		
//		if(response.getStatusCode().is2xxSuccessful()) {
//			Usuario u = response.getBody();
//			HttpEntity<Usuario> requestEntity2 = new HttpEntity<>(u, headers);
//			ResponseEntity<Usuario> response2 = 
//		}
		//headers.setContentType(MediaType.APPLICATION_JSON);
		return response;		
		
	}
	
	
	 @Transactional
   public ViajeResponseDto save(ViajeRequestDto request ){
		 
		 final var m = this.monopatinRepository.findById(request.getMonopatinId())
					.orElseThrow(() -> new NotFoundException(String.format("No existe el monopatin con id%s", request.getMonopatinId())));
	 	final var p = this.paradaRepository.findById(request.getParadaFinalId())
	 					.orElseThrow( () -> new NotFoundException(String.format("No existe la parada con id %s", request.getParadaFinalId() ) ) );
	     	
	    Viaje viaje = new Viaje(request.getInicioViaje(), request.getFinViaje(), request.getCosto(), m, request.getUsuarioId(), request.getCuentaId(),
	    		   p, request.getKmsRecorridos(), request.getTiempoPausa(), request.isPausaActiva());
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
       final var m = this.monopatinRepository.findById(request.getMonopatinId())
				.orElseThrow(() -> new NotFoundException(String.format("No existe el monopatin con id%s", request.getMonopatinId())));
       
       final var parada = this.paradaRepository.findById(request.getParadaFinalId())
				.orElseThrow( () -> new NotFoundException(String.format("No existe la parada con id %s", request.getParadaFinalId() ) ) );
       
       viaje.setCosto(request.getCosto());
       viaje.setCuentaId(request.getCuentaId());
       viaje.setFinViaje(request.getFinViaje());
       viaje.setInicioViaje(request.getInicioViaje());
       viaje.setKmsRecorridos(request.getKmsRecorridos());
       viaje.setMonopatin(m);
       viaje.setParadaFinal(parada);
       viaje.setPausaActiva(request.isPausaActiva());
       viaje.setTiempoPausa(request.getTiempoPausa());
       viaje.setUsuarioId(request.getUsuarioId());
       return this.viajeRepository.save(viaje);
   }

}
