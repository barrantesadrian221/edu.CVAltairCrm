package edu.CVAltair.dto;


/** Datos de respuesta de las operaciones de experiencia.
  * @param idExperiencia
 * @param puesto
 * @param empresa
 * @param periodo
 * @param descripcion
     *
     * @author ABR
     */
public record ExperienciaResponseDto(
        Long idExperiencia,
        String puesto,
        String empresa,
        String periodo,
        String descripcion
) {
}
