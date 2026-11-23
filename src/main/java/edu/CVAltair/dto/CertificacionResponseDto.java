package edu.CVAltair.dto;

import java.time.LocalDate;

public record CertificacionResponseDto(
        Long idCertificacion,
        String titulo,
        String emision,
        LocalDate fechaObtencion
) {
}
