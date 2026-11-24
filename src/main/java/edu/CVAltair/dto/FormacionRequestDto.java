package edu.CVAltair.dto;


/** Datos de entrada para las operaciones de formación.
  * @param titulo
 * @param centro
 * @param periodo
 * @param enCurso
     *
     * @author ABR
     */
public record FormacionRequestDto(
        String titulo,
        String centro,
        String periodo,
        boolean enCurso
) {
}
