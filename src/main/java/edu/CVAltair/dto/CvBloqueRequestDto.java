package edu.CVAltair.dto;


/** Datos de entrada para las operaciones de bloque del currículum.
  * @param tipo
 * @param idElemento
     *
     * @author ABR
     */
public record CvBloqueRequestDto(
        TipoBloqueCv tipo,
        Long idElemento
) {
}
