package edu.CVAltair.service;

import edu.CVAltair.dto.AlumnoRequestDto;
import edu.CVAltair.dto.AlumnoResponseDto;
import edu.CVAltair.dto.TutorialResponseDto;

import java.util.List;
import java.util.Optional;


/**
 * Define el contrato de operaciones relacionado con alumno.
 * @author ABR
 */
public interface AlumnoService {
    /**
     * Devuelve todos los registros de alumnos.
     * @return lista con los registros obtenidos.
     */
    List<AlumnoResponseDto> listarTodos();
    /**
     * Crea alumno con los datos proporcionados.
     * @param crear
     * @return resultado obtenido al ejecutar la operación.
     */
    AlumnoResponseDto crear(AlumnoRequestDto crear);

    /**
     * Busca alumno por identificador.
     * @param id
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<AlumnoResponseDto> buscarPorId(Long id);
    /**
     * Busca alumno por DNI.
     * @param dni
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<AlumnoResponseDto> buscarPorDni(String dni);
    /**
     * Busca alumno por correo electrónico.
     * @param email
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<AlumnoResponseDto> buscarPorEmail(String email);
    /**
     * Busca alumno por número seguridad social.
     * @param numeroSeguridadSocial
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<AlumnoResponseDto> buscarPorNumeroSeguridadSocial(String numeroSeguridadSocial);

    /**
     * Comprueba si ya existe alumno con el criterio indicado.
     * @param dni
     * @return true si se cumple la condición comprobada; false en caso contrario.
     */
    boolean existePorDni(String dni);
    /**
     * Comprueba si ya existe alumno con el criterio indicado.
     * @param email
     * @return true si se cumple la condición comprobada; false en caso contrario.
     */
    boolean existePorEmail(String email);
    /**
     * Comprueba si ya existe alumno con el criterio indicado.
     * @param numeroSeguridadSocial
     * @return true si se cumple la condición comprobada; false en caso contrario.
     */
    boolean existePorNumeroSeguridadSocial(String numeroSeguridadSocial);

    /**
     * Actualiza alumno con los datos proporcionados.
     * @param id
     * @param datosActalizados
     * @return resultado obtenido al ejecutar la operación.
     */
    AlumnoResponseDto actualizarPorId(Long id, AlumnoRequestDto datosActalizados);
    /**
     * Actualiza alumno con los datos proporcionados.
     * @param dni
     * @param datosActalizados
     * @return resultado obtenido al ejecutar la operación.
     */
    AlumnoResponseDto actualizarPorDni(String dni, AlumnoRequestDto datosActalizados);

    /**
     * Devuelve el estado y los pasos del tutorial.
     * Implementado con ayuda de IA.
     *
     * @param id
     * @return estado y contenido del tutorial
     */
    TutorialResponseDto consultarTutorial(Long id);

    /**
     * Guarda que el alumno terminó el tutorial.
     * Implementado con ayuda de IA.
     *
     * @param id
     * @return estado actualizado del tutorial
     */
    TutorialResponseDto completarTutorial(Long id);

    /**
     * Guarda que el alumno decidió omitir el tutorial.
     * Implementado con ayuda de IA.
     *
     * @param id
     * @return estado actualizado del tutorial
     */
    TutorialResponseDto omitirTutorial(Long id);

    /**
     * Elimina alumno según el criterio indicado.
     * @param id
     *
     */
    void eliminarPorId(Long id);
    /**
     * Elimina alumno según el criterio indicado.
     * @param dni
     */
    void eliminarPorDni (String dni);
}
