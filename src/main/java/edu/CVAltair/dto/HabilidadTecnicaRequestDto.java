package edu.CVAltair.dto;


/** Datos de entrada para las operaciones de habilidad técnica.
  * @param nombre
 * @param descripcion
 * @param destacada
     *
     * @author ABR
     */
public record HabilidadTecnicaRequestDto(
        String nombre,
        String descripcion,
        boolean destacada
) {
}
