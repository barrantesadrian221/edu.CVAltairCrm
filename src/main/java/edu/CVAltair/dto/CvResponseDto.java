package edu.CVAltair.dto;

import java.util.List;


/** Datos de respuesta de las operaciones de currículum.
  * @param idCV
 * @param alumno
 * @param experiencias
 * @param formaciones
 * @param habilidades
 * @param idiomas
 * @param certificaciones
 * @param ordenBloques
     *
     * @author ABR
     */
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
