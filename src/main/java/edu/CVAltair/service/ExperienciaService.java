package edu.CVAltair.service;

import edu.CVAltair.dto.ExperienciaRequestDto;
import edu.CVAltair.dto.ExperienciaResponseDto;

import java.util.List;
import java.util.Optional;


/** Define el contrato de operaciones relacionado con experiencia.
     *
     * @author ABR
     */
public interface ExperienciaService {
    /**
     * Ejecuta la operación «listar todas» para experiencia.
     * @return lista con los registros obtenidos.
     */
    List<ExperienciaResponseDto> listarTodas();
    /**
     * Ejecuta la operación «listar por alumno» para experiencia.
     * @param idAlumno
     * @return lista con los registros obtenidos.
     */
    List<ExperienciaResponseDto> listarPorAlumno(Long idAlumno);
    /**
     * Busca experiencia por identificador.
     * @param id
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<ExperienciaResponseDto> buscarPorId(Long id);
    /**
     * Crea experiencia con los datos proporcionados.
     * @param idAlumno
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    ExperienciaResponseDto crearParaAlumno(Long idAlumno, ExperienciaRequestDto datos);
    /**
     * Actualiza experiencia con los datos proporcionados.
     * @param id
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    ExperienciaResponseDto actualizar(Long id, ExperienciaRequestDto datos);
    /**
     * Elimina experiencia según el criterio indicado.
     * @param id
     */
    void eliminar(Long id);
}
