package com.mx.sda.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mx.sda.dto.OficiosDto;
import com.mx.sda.exception.ObjectNotFoundException;
import com.mx.sda.persistencia.entity.Alumnos;
import com.mx.sda.persistencia.entity.Oficios;
import com.mx.sda.persistencia.entity.Alumnos.AlumnoEstatus;
import com.mx.sda.persistencia.entity.Oficios.TipoOficio;
import com.mx.sda.persistencia.repocitory.OficiosRepository;
import com.mx.sda.service.OficiosService;

@Service
public class OficiosServiceImpl implements OficiosService {
	
	@Autowired
	private OficiosRepository oficiosRepository;

	@Override
	public Page<Oficios> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return oficiosRepository.findAll(pageable);
	}

	@Override
	public Optional<Oficios> findOneById(Integer oficioId) {
		// TODO Auto-generated method stub
		return oficiosRepository.findById(oficioId);
	}

	@Override
	public Oficios createOne(OficiosDto oficiosDto) {
		// TODO Auto-generated method stub
		Oficios oficios = new Oficios();
		oficios.setAsunto(oficiosDto.getAsunto());
		oficios.setDirigido(oficiosDto.getDirigido());
		oficios.setTipoOficio(TipoOficio.ENVIADO);
		oficios.setFechaRegistro(oficiosDto.getFechaRegistro());
		return oficiosRepository.save(oficios);
	}

	@Override
	public Oficios updateOneById(Integer oficioId, OficiosDto oficiosDto) {
		// TODO Auto-generated method stub
		Oficios oficioBd = oficiosRepository.findById(oficioId).orElseThrow(()-> new ObjectNotFoundException("No se encuentra el oficio"));
		oficioBd.setAsunto(oficiosDto.getAsunto());
		oficioBd.setDirigido(oficiosDto.getDirigido());
		oficioBd.setFechaRegistro(oficioBd.getFechaRegistro());
		return oficiosRepository.save(oficioBd);
	}

	@Override
	public Oficios deleteOneById(Integer oficioId) {
		// TODO Auto-generated method stub
		if (oficiosRepository.existsById(oficioId)) {
	        oficiosRepository.deleteById(oficioId);
	    } else {
	        throw new ObjectNotFoundException("No se encuentra el oficio");
	    }
		
		return null;
		
	}
	
	

}
