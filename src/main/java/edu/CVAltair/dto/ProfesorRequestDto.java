package edu.CVAltair.dto;


/** Datos de entrada para las operaciones de profesor.
  * @param dni
 * @param gmail
 * @param nombre
 * @param apellidos
 * @param departamento
     *
     * @author ABR
     */
public record ProfesorRequestDto(
        String dni,
        String gmail,
        String nombre,
        String apellidos,
        String departamento
) {
}
