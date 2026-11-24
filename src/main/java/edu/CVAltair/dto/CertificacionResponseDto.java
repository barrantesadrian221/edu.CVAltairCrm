package edu.CVAltair.dto;

import java.time.LocalDate;


/** Datos de respuesta de las operaciones de certificación.
  * @param idCertificacion
 * @param titulo
 * @param emision
 * @param fechaObtencion
     *
     * @author ABR
     */
public record CertificacionResponseDto(
        Long idCertificacion,
        String titulo,
        String emision,
        LocalDate fechaObtencion
) {
}
