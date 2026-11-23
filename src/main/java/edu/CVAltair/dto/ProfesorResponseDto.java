package edu.CVAltair.dto;

public record ProfesorResponseDto(
        Long idProfesor,
        String dni,
        String gmail,
        String nombre,
        String apellidos,
        String departamento
) {
}
