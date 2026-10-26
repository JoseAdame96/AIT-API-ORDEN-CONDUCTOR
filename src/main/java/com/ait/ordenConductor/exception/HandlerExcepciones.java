package com.ait.ordenConductor.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


@Slf4j
@ControllerAdvice
public class HandlerExcepciones {

  private static String logExcepcionCapturada = "Excepcion capturada: {}";

  @ExceptionHandler(value = {ExcepcionGenerica.class})
  public ResponseEntity<ExceptionDTO> handleApiRequestException(ExcepcionGenerica e) {
    log.info(logExcepcionCapturada, e.getMessage());
    log.info("ExcepcionGenerica");
    ExceptionDTO excepcion = new ExceptionDTO();
    excepcion.setMensaje(e.getMensaje());
    return new ResponseEntity<>(excepcion, e.getHttpStatus());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionDTO> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		log.info(".class");

    List<String> errors = ex.getBindingResult().getAllErrors().stream()
      .map(err -> err.unwrap(ConstraintViolation.class))
      .map(err -> String.format("'%s': %s", err.getPropertyPath(), err.getMessage())).toList();

    ExceptionDTO excepcion = new ExceptionDTO();
    excepcion.setMensaje(ex.getMessage());
    excepcion.setDetalles(errors);

		return new ResponseEntity<>(excepcion, HttpStatus.BAD_REQUEST);
	}

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ExceptionDTO> handleConstraintViolationException(ConstraintViolationException ex) {
    log.info("ConstraintViolationException");

    log.info(logExcepcionCapturada, ex.getMessage());
    List<String> listaErrores = new ArrayList<>();

    Map<String, ConstraintViolationException> maps = new HashMap<>();
    maps.put("Error", ex);
    for (Map.Entry<String, ConstraintViolationException> resul : maps.entrySet()) {
      listaErrores.add(resul.getValue().getLocalizedMessage());
    }

    ExceptionDTO excepcion = new ExceptionDTO();
    excepcion.setMensaje(ex.getMessage());
    excepcion.setDetalles(listaErrores);

    return new ResponseEntity<>(excepcion, HttpStatus.BAD_REQUEST);
  }
}
