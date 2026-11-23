package edu.CVAltair.dto;

import java.util.List;

public record CvContenidoRequestDto(
        List<Long> idsExperiencias,
        List<Long> idsFormaciones,
        List<Long> idsHabilidades,
        List<Long> idsIdiomas,
        List<Long> idsCertificaciones,
        List<CvBloqueRequestDto> ordenBloques
) {
}
