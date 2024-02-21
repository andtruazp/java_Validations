package com.mx.sda.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
import com.mx.sda.service.AlumnosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/alumnos")
public class AlumnosController {
	@Autowired
	private AlumnosService alumnosService;
	
	@GetMapping
	public ResponseEntity<Page<Alumnos>> findAll(Pageable pageable){
		Page<Alumnos> alumnosPage = alumnosService.findAll(pageable);
		if(alumnosPage.hasContent()) {
			return ResponseEntity.ok(alumnosPage);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/:alumnoId")
	public ResponseEntity<Alumnos> findOneById(@PathVariable Integer alumnoId){
		Optional<Alumnos> alumnos = alumnosService.findOneById(alumnoId);
		if(alumnos.isPresent()) {
			return ResponseEntity.ok(alumnos.get());
		}
		return ResponseEntity.notFound().build();
	}
	@PostMapping
	public ResponseEntity<?> createOne(
			@RequestBody @Valid AlumnosDto alumnosDto, BindingResult result){
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		Alumnos alumnos = alumnosService.createOne(alumnosDto);
				return ResponseEntity.status(HttpStatus.CREATED).body(alumnos);
	}
	
	@PutMapping("/{alumnoId}")
	public ResponseEntity<?> updateOneById(
			@PathVariable Integer alumnoId, @RequestBody @Valid
			AlumnosDto alumnosDto, BindingResult result){
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		Alumnos alumnos = alumnosService.updateOneById(alumnoId, alumnosDto);
				return ResponseEntity.ok(alumnos);
	}
	@PutMapping("/{alumnoId}/disabled")
	public ResponseEntity<Alumnos> diseableOneById(@PathVariable Integer alumnoId){
		Alumnos alumnos = alumnosService.diseableOneById(alumnoId);
		return ResponseEntity.ok(alumnos);
	}
	
	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err ->{
			errores.put(err.getField(), "El campo " + err.getField()+" "+err.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errores);
	}
}
