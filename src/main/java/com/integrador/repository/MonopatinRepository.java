package com.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrador.domain.Monopatin;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long>{

}
