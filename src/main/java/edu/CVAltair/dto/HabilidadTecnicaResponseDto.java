package edu.CVAltair.dto;

public record HabilidadTecnicaResponseDto(
        Long idHabilidadTecnica,
        String nombre,
        String descripcion,
        boolean destacada
) {
}
