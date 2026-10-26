package com.ait.ordenConductor.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

@Getter 
@Setter
public class ExcepcionGenerica extends RuntimeException {
  private static final long serialVersionUID = 2056384477729240808L;

  private final String mensaje;
  private final List<String> detalles;
  private final HttpStatus httpStatus;

  public ExcepcionGenerica(String mensaje, List<String> detalles, HttpStatus httpStatus) {
    this.mensaje = mensaje;
    this.detalles = new ArrayList<>(detalles);
    this.httpStatus = httpStatus;
  }


}
