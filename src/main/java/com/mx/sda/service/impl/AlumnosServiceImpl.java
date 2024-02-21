package com.mx.sda.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mx.sda.dto.AlumnosDto;
import com.mx.sda.exception.ObjectNotFoundException;
import com.mx.sda.persistencia.entity.Alumnos;
import com.mx.sda.persistencia.entity.Alumnos.AlumnoEstatus;
import com.mx.sda.persistencia.entity.Grupo;
import com.mx.sda.persistencia.repocitory.AlumnosRepository;
import com.mx.sda.service.AlumnosService;

@Service
public class AlumnosServiceImpl implements AlumnosService {
	
	@Autowired
	private AlumnosRepository alumnosRepository;

	@Override
	public Page<Alumnos> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return alumnosRepository.findAll(pageable);
	}

	@Override
	public Optional<Alumnos> findOneById(Integer alumnoId) {
		// TODO Auto-generated method stub
		return alumnosRepository.findById(alumnoId);
	}

	@Override
	public Alumnos createOne(AlumnosDto alumnosDto) {
		// TODO Auto-generated method stub
		Alumnos alumnos = new Alumnos();
		alumnos.setNombre(alumnosDto.getNombre());
		alumnos.setNumeroControl(alumnosDto.getNumeroControl());
		alumnos.setCalificacion(alumnosDto.getCalificacion());
		alumnos.setEstatus(AlumnoEstatus.REGULAR);
		
		Grupo grupo = new Grupo();
		grupo.setId(alumnosDto.getGrupo_Id());
		
		alumnos.setGrupo(grupo);
		
		return alumnosRepository.save(alumnos);
	}

	@Override
	public Alumnos updateOneById(Integer alumnoId, AlumnosDto alumnosDto) {
		Alumnos alumnoBd = alumnosRepository.findById(alumnoId).orElseThrow(()-> new ObjectNotFoundException("No se encuentra el alumno"));
		alumnoBd.setNombre(alumnosDto.getNombre());
		alumnoBd.setNumeroControl(alumnosDto.getNumeroControl());
		alumnoBd.setCalificacion(alumnosDto.getCalificacion());
		
		Grupo grupo  = new Grupo();
		grupo.setId(alumnosDto.getGrupo_Id());
		return alumnosRepository.save(alumnoBd);
	}

	@Override
	public Alumnos diseableOneById(Integer alumnoId) {
		Alumnos alumnoBd = alumnosRepository.findById(alumnoId).orElseThrow(()-> 
		new ObjectNotFoundException("No se encuentra el alumno"));
		alumnoBd.setEstatus(AlumnoEstatus.BAJA_TEMPORAL);
		return alumnosRepository.save(alumnoBd);
	}

}
