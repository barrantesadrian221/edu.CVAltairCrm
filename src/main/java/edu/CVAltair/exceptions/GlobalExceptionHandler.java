package edu.CVAltair.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail manejarNoEncontrado(EntityNotFoundException exception) {
        ProblemDetail detalle = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        detalle.setTitle("Recurso no encontrado");
        detalle.setType(URI.create("https://api.altair.edu/errors/not-found"));
        detalle.setProperty("timestamp", Instant.now());
        return detalle;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail manejarDuplicadoOParametroInvalido(IllegalArgumentException exception) {
        ProblemDetail detalle = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                exception.getMessage()
        );
        detalle.setTitle("Conflicto de datos");
        detalle.setType(URI.create("https://api.altair.edu/errors/conflict"));
        detalle.setProperty("timestamp", Instant.now());
        return detalle;
    }
}
