package com.TPO.gestionturnos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Ya hay una especialidad con ese nombre")
public class EspecialidadExistenteException extends Exception {

}