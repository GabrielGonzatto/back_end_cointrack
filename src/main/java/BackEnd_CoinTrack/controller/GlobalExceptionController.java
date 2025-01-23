package BackEnd_CoinTrack.controller;

import BackEnd_CoinTrack.infra.exceptions.RespostaExceptionDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;


@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RespostaExceptionDTO> handleConstraintViolationException (ConstraintViolationException ex) {
        return new ResponseEntity<>(new RespostaExceptionDTO(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), ex.getConstraintViolations().iterator().next().getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RespostaExceptionDTO> handleDataIntegrityViolationException (DataIntegrityViolationException ex) {
        return new ResponseEntity<>(new RespostaExceptionDTO(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), "Credenciais inválidas."), HttpStatus.CONFLICT);
    }

}
