package com.mx.sda.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.sda.dto.AlumnosDto;
import com.mx.sda.dto.GrupoDto;
import com.mx.sda.persistencia.entity.Alumnos;
import com.mx.sda.persistencia.entity.Grupo;
import com.mx.sda.service.GrupoService;
import com.mx.sda.validation.GrupoValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/grupo")
public class GrupoController {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(GrupoController.class);
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private GrupoValidation validation;
	
	@GetMapping
	public ResponseEntity<Page<Grupo>> findAll(Pageable pageable){
		//LOGGER.warn(null);
		//LOGGER.info(null);
		//LOGGER.error(null);
		Page<Grupo> grupoPage = grupoService.findAll(pageable);
		if(grupoPage.hasContent()) {
			return ResponseEntity.ok(grupoPage);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/:grupoId")
	public ResponseEntity<Grupo> findOneById(@PathVariable Integer grupoId){
		Optional<Grupo> grupo = grupoService.findOneById(grupoId);
		if(grupo.isPresent()) {
			return ResponseEntity.ok(grupo.get());
		}
		return ResponseEntity.notFound().build();
	}
	@PostMapping
	public ResponseEntity<?> createOne(
			@RequestBody @Valid GrupoDto grupoDto, BindingResult result){
		validation.validate(grupoDto, result);
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		Grupo grupo = grupoService.createOne(grupoDto);
				return ResponseEntity.status(HttpStatus.CREATED).body(grupo);
	}
	
	@PutMapping("/{grupoId}")
	public ResponseEntity<?> updateOneById(
			@PathVariable Integer grupoId, @RequestBody @Valid
			GrupoDto grupoDto, BindingResult result){
		validation.validate(grupoDto, result);
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		Grupo grupo = grupoService.updateOneById(grupoId, grupoDto);
				return ResponseEntity.ok(grupo);
	}
	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err ->{
			errores.put(err.getField(), "El campo " + err.getField()+" "+err.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errores);
	}

	@PutMapping("/{grupoId}/disabled")
	public ResponseEntity<Grupo> diseableOneById(@PathVariable Integer grupoId){
		Grupo grupo = grupoService.diseableOneById(grupoId);
		return ResponseEntity.ok(grupo);
	}
}
