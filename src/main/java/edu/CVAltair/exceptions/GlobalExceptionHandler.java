package edu.CVAltair.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;


/**
 * Representa gestor global de excepciones en la aplicación.

 * @author ABR
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * EXCEPCION DE ENTIDAD NO ENCONTRADA
     * @param exception
     * @return Error en algun punto del HTTP(404)
     */
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

    /**
     * EXCEPCION DE ARGUMENTO ILEGAL
     * @param exception
     * @return Error en algun punto del HTTP(409)
     */
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
