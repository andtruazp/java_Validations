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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.sda.dto.OficiosDto;
import com.mx.sda.persistencia.entity.Oficios;
import com.mx.sda.service.OficiosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/oficios")
public class OficiosController {
	@Autowired
	private OficiosService oficiosService;
	
	@GetMapping
	public ResponseEntity<Page<Oficios>> findAll(Pageable pageable){
		Page<Oficios> oficiosPage = oficiosService.findAll(pageable);
		if(oficiosPage.hasContent()) {
			return ResponseEntity.ok(oficiosPage);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{oficioId}")
	public ResponseEntity<Oficios> findOneById(@PathVariable Integer oficioId){
		Optional<Oficios> oficios = oficiosService.findOneById(oficioId);
		if(oficios.isPresent()) {
			return ResponseEntity.ok(oficios.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> createOne(
			@RequestBody @Valid OficiosDto oficiosDto, BindingResult result){
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		Oficios oficios = oficiosService.createOne(oficiosDto);
				return ResponseEntity.status(HttpStatus.CREATED).body(oficios);
	}
	
	@PutMapping("/{oficioId}")
	public ResponseEntity<?> updateOneById(
			@PathVariable Integer oficioId, @RequestBody @Valid
			OficiosDto oficiosDto, BindingResult result){
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		Oficios oficios = oficiosService.updateOneById(oficioId, oficiosDto);
				return ResponseEntity.ok(oficios);
	}
	
	@DeleteMapping("/{oficioId}")
	public ResponseEntity<Oficios> deleteOneById(@PathVariable Integer oficioId){
		Oficios oficios = oficiosService.deleteOneById(oficioId);
		return ResponseEntity.ok(oficios);
	}
	
	
	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err ->{
			errores.put(err.getField(), "El campo " + err.getField()+" "+err.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errores);
	}

}
