package com.mx.sda.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mx.sda.dto.OficiosDto;
import com.mx.sda.persistencia.entity.Oficios;

public interface OficiosService {
	
	Page<Oficios> findAll(Pageable pageable);
	
	Optional<Oficios> findOneById(Integer oficioId);
	
	Oficios createOne(OficiosDto oficiosDto);
	
	Oficios updateOneById(Integer oficioId, OficiosDto oficiosDto);
	
	Oficios deleteOneById(Integer oficioId);

}
