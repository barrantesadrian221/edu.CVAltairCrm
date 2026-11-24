package edu.CVAltair.dto;


/** Datos de respuesta de las operaciones de bloque del currículum.
  * @param tipo
 * @param idElemento
 * @param posicion
     *
     * @author ABR
     */
public record CvBloqueResponseDto(
        TipoBloqueCv tipo,
        Long idElemento,
        int posicion
) {
}
