package com.mx.sda.persistencia.repocitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.sda.persistencia.entity.Grupo;

public interface GruposRepository extends JpaRepository<Grupo, Integer> {

}
