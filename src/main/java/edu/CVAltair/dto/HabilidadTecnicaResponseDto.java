package edu.CVAltair.dto;


/** Datos de respuesta de las operaciones de habilidad técnica.
  * @param idHabilidadTecnica
 * @param nombre
 * @param descripcion
 * @param destacada
     *
     * @author ABR
     */
public record HabilidadTecnicaResponseDto(
        Long idHabilidadTecnica,
        String nombre,
        String descripcion,
        boolean destacada
) {
}
