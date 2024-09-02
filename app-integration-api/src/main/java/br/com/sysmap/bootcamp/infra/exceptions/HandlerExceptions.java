package br.com.sysmap.bootcamp.infra.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import se.michaelthelin.spotify.exceptions.detailed.BadRequestException;

@RestControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestException(BadRequestException msg) {
        return ResponseEntity.badRequest().body(msg.getMessage());
    }
}
