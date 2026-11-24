package edu.CVAltair.dto;


/** Datos de entrada para las operaciones de filtro del currículum.
  * @param certificacion
 * @param habilidad
 * @param idioma
 * @param nivelIdioma
     *
     * @author ABR
     */
public record CvFiltroRequestDto(
        String certificacion,
        String habilidad,
        String idioma,
        String nivelIdioma
) {
}
