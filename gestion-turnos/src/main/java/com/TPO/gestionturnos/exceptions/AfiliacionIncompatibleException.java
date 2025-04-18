package com.TPO.gestionturnos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Ya hay una afiliacion incompatible con esas caracteristicas")
public class AfiliacionIncompatibleException extends Exception {

}