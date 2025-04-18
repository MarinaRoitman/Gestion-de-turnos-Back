package com.TPO.gestionturnos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Ya existe un plan con ese nombre y obra social")
public class PlanExistenteException extends Exception {

}