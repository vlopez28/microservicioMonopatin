package com.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrador.domain.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long>{

}
