package edu.CVAltair.dto;

public record CvBloqueResponseDto(
        TipoBloqueCv tipo,
        Long idElemento,
        int posicion
) {
}
