package edu.CVAltair.dto;

import java.util.List;

/** Estado y contenido que la interfaz necesita para mostrar el tutorial.
 * Implementado con ayuda de IA.
 *
 * @param idAlumno
 * @param estado
 * @param mostrarTutorial
 * @param pasos
     *
     * @author ABR
     */
public record TutorialResponseDto(
        Long idAlumno,
        EstadoTutorial estado,
        boolean mostrarTutorial,
        List<TutorialPasoDto> pasos
) {
}
