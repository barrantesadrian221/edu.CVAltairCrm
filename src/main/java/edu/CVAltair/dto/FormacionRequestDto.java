package edu.CVAltair.dto;

public record FormacionRequestDto(
        String titulo,
        String centro,
        String periodo,
        boolean enCurso
) {
}
