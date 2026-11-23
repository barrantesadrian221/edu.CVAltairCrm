package edu.CVAltair.dto;

import java.time.LocalDate;

public record AlumnoRequestDto(
        String nombre,
        String apellidos,
        String dni,
        String email,
        String numeroSeguridadSocial,
        Boolean tutorialCompletado,
        LocalDate fechaNacimiento
) {
}
