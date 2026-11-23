package edu.CVAltair.dto;

import java.time.LocalDate;

public record CertificacionRequestDto(
        String titulo,
        String emision,
        LocalDate fechaObtencion
) {
}
