package br.com.sysmap.bootcamp.domain.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class HanlderExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleCreateUserException(MethodArgumentNotValidException msg) {
        String rawMsg = msg.getMessage().substring(msg.getMessage().lastIndexOf("default message"));

        // default message [não deve estar em branco]]
        String responseMsg = rawMsg.subSequence(17, rawMsg.length() - 3).toString();

        return ResponseEntity.badRequest().body(responseMsg);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleCreateUserException() {

        return ResponseEntity.badRequest().body("User Not Found!");
    }

}
