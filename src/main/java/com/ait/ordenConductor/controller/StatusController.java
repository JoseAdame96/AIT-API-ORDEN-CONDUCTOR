package com.ait.ordenConductor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Controller
@RequestMapping
public class StatusController {

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        log.info("Implementacion de log");
        return new ResponseEntity<>("OK", OK);
    }
}