package com.widyatama.widytamahotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.validation.ConstraintDeclarationException; 

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ConstraintDeclarationException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1970736590681858807L;

	public ResourceNotFoundException(String message) {
		System.out.print(message);
	}
}
