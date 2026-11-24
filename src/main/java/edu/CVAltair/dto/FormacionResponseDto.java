package edu.CVAltair.dto;


/** Datos de respuesta de las operaciones de formación.
  * @param idFormacion
 * @param titulo
 * @param centro
 * @param periodo
 * @param enCurso
     *
     * @author ABR
     */
public record FormacionResponseDto(
        Long idFormacion,
        String titulo,
        String centro,
        String periodo,
        boolean enCurso
) {
}
