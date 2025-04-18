package com.TPO.gestionturnos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Ya existe un profesional con ese mail o matricula")
public class ProfesionalExistenteException extends Exception {

}