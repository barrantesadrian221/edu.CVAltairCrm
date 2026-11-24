package edu.CVAltair.dto;

import java.time.LocalDate;


/** Datos de entrada para las operaciones de certificación.
  * @param titulo
 * @param emision
 * @param fechaObtencion
     *
     * @author ABR
     */
public record CertificacionRequestDto(
        String titulo,
        String emision,
        LocalDate fechaObtencion
) {
}
