package edu.CVAltair.dto;


/** Datos de entrada para las operaciones de experiencia.
  * @param puesto
 * @param empresa
 * @param periodo
 * @param descripcion
     *
     * @author ABR
     */
public record ExperienciaRequestDto(
        String puesto,
        String empresa,
        String periodo,
        String descripcion
) {
}
