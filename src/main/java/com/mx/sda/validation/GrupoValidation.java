package com.mx.sda.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sda.dto.GrupoDto;

@Component
public class GrupoValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return GrupoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GrupoDto grupoDto = (GrupoDto)target;
		if(grupoDto.getNombre() == null || grupoDto.getNombre().isEmpty()) {
			errors.rejectValue("nombre", null, " es requerido");
		}else {
			if(grupoDto.getNombre().length()<4 || grupoDto.getNombre().length() >10) {
				errors.rejectValue("nombre", null, " debe estar entre 4 y 10 caracteres");
			}
		}
		
	}

}
