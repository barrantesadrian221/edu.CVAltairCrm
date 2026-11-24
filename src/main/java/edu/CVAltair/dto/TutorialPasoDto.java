package edu.CVAltair.dto;

/** Datos que describen un paso del tutorial.
 * Implementado con ayuda de IA.
 *
 * @param numero
 * @param titulo
 * @param descripcion
 * @param metodo
 * @param endpoint
     *
     * @author ABR
     */
public record TutorialPasoDto(
        int numero,
        String titulo,
        String descripcion,
        String metodo,
        String endpoint
) {
}
