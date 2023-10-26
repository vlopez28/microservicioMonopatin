package com.integrador.service;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.integrador.service.dto.monopatin.*;
import com.integrador.domain.Monopatin;
import com.integrador.repository.MonopatinRepository;
import com.integrador.service.exception.*;



@Service
public class MonopatinService {
	
	 private  MonopatinRepository monopatinRepository;
	 
	 public MonopatinService( MonopatinRepository monopatinRepository) { 
		 this.monopatinRepository = monopatinRepository;
	 }
	 

	 @Transactional
    public MonopatinResponseDto save(MonopatinRequestDto request ){
		 System.out.println(request);
        Monopatin monopatin = new Monopatin(request);
        Monopatin result = this.monopatinRepository.save(monopatin);
        return new MonopatinResponseDto(result);
    }
	    
	    
  

    @Transactional
    public List<MonopatinResponseDto> findAll(){
        return this.monopatinRepository.findAll().stream().map( MonopatinResponseDto::new ).toList();
    }

    @Transactional
    public MonopatinResponseDto findById( Long id ){
        return this.monopatinRepository.findById( id )
                .map( MonopatinResponseDto::new )
                .orElseThrow( () -> new NotFoundException("Monopatin", id ) );
    }
    
    @Transactional
    public void delete(Long id) {
    	this.monopatinRepository.delete(this.monopatinRepository.findById(id).orElseThrow(
                () -> new NotFoundException("ID de monopatin invalido:" + id)));
    }

    @Transactional
    public Monopatin update(Long id, MonopatinRequestDto request) {
        Monopatin monopatin = this.monopatinRepository.findById(id).orElseThrow(
                () -> new NotFoundException("ID de monopatin inválido: " + id));
       
        monopatin.setCantidadViajes(request.getCantidadViajes());
        monopatin.setEstado(request.getEstado());
        monopatin.setDisponible(request.isDisponible());
        monopatin.setKmsMant(request.getkmsMantenimiento());
        monopatin.setKmsRecorridos(request.getKmsRecorridos());
        monopatin.setTiempoPausado(request.getTiempoPausado());
        monopatin.setTiempoUsoTotal(request.getTiempoUsoTotal());
        monopatin.setUbicacion(request.getUbicacion());
        return this.monopatinRepository.save(monopatin);
    }

}
