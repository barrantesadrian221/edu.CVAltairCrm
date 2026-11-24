package edu.CVAltair.dto;


/** Datos de respuesta de las operaciones de profesor.
  * @param idProfesor
 * @param dni
 * @param gmail
 * @param nombre
 * @param apellidos
 * @param departamento
     *
     * @author ABR
     */
public record ProfesorResponseDto(
        Long idProfesor,
        String dni,
        String gmail,
        String nombre,
        String apellidos,
        String departamento
) {
}
