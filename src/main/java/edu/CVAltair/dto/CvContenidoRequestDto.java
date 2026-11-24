package edu.CVAltair.dto;

import java.util.List;


/** Datos de entrada para las operaciones de contenido del currículum.
  * @param idsExperiencias
 * @param idsFormaciones
 * @param idsHabilidades
 * @param idsIdiomas
 * @param idsCertificaciones
 * @param ordenBloques
     *
     * @author ABR
     */
public record CvContenidoRequestDto(
        List<Long> idsExperiencias,
        List<Long> idsFormaciones,
        List<Long> idsHabilidades,
        List<Long> idsIdiomas,
        List<Long> idsCertificaciones,
        List<CvBloqueRequestDto> ordenBloques
) {
}
