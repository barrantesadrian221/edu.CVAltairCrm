package edu.CVAltair.dto;

public record ExperienciaResponseDto(
        Long idExperiencia,
        String puesto,
        String empresa,
        String periodo,
        String descripcion
) {
}
