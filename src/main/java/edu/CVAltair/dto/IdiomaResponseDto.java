package edu.CVAltair.dto;


/** Datos de respuesta de las operaciones de idioma.
  * @param idIdioma
 * @param nombre
 * @param nivel
     *
     * @author ABR
     */
public record IdiomaResponseDto(
        Long idIdioma,
        String nombre,
        String nivel
) {
}
