package edu.CVAltair.dto;

import java.time.LocalDate;


/** Datos de respuesta de las operaciones de alumno.
  * @param idAlumno
 * @param nombre
 * @param apellidos
 * @param dni
 * @param email
 * @param numeroSeguridadSocial
 * @param tutorialCompletado
 * @param fechaNacimiento
 * @param idCV
     *
     * @author ABR
     */
public record AlumnoResponseDto(
        Long idAlumno,
        String nombre,
        String apellidos,
        String dni,
        String email,
        String numeroSeguridadSocial,
        Boolean tutorialCompletado,
        LocalDate fechaNacimiento,
        Long idCV
) {
}
