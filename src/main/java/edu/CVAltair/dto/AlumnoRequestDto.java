package edu.CVAltair.dto;

import java.time.LocalDate;


/** Datos de entrada para las operaciones de alumno.
  * @param nombre
 * @param apellidos
 * @param dni
 * @param email
 * @param numeroSeguridadSocial
 * @param tutorialCompletado
 * @param fechaNacimiento
     *
     * @author ABR
     */
public record AlumnoRequestDto(
        String nombre,
        String apellidos,
        String dni,
        String email,
        String numeroSeguridadSocial,
        Boolean tutorialCompletado,
        LocalDate fechaNacimiento
) {
}
