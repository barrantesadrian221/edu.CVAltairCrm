package edu.CVAltair.dto;

public record FormacionResponseDto(
        Long idFormacion,
        String titulo,
        String centro,
        String periodo,
        boolean enCurso
) {
}
