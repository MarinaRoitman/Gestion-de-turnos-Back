package com.TPO.gestionturnos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "No existe el plan")
public class PlanInexistenteException extends Exception {

}