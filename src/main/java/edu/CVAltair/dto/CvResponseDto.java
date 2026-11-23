package edu.CVAltair.dto;

import java.util.List;

public record CvResponseDto(
        Long idCV,
        AlumnoResponseDto alumno,
        List<ExperienciaResponseDto> experiencias,
        List<FormacionResponseDto> formaciones,
        List<HabilidadTecnicaResponseDto> habilidades,
        List<IdiomaResponseDto> idiomas,
        List<CertificacionResponseDto> certificaciones,
        List<CvBloqueResponseDto> ordenBloques
) {
}
