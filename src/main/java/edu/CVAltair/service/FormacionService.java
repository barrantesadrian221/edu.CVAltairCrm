package edu.CVAltair.service;

import edu.CVAltair.dto.FormacionRequestDto;
import edu.CVAltair.dto.FormacionResponseDto;

import java.util.List;
import java.util.Optional;


/** Define el contrato de operaciones relacionado con formación.
     *
     * @author ABR
     */
public interface FormacionService {
    /**
     * Ejecuta la operación «listar todas» para formación.
     * @return lista con los registros obtenidos.
     */
    List<FormacionResponseDto> listarTodas();
    /**
     * Ejecuta la operación «listar por alumno» para formación.
     * @param idAlumno
     * @return lista con los registros obtenidos.
     */
    List<FormacionResponseDto> listarPorAlumno(Long idAlumno);
    /**
     * Busca formación por identificador.
     * @param id
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<FormacionResponseDto> buscarPorId(Long id);
    /**
     * Crea formación con los datos proporcionados.
     * @param idAlumno
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    FormacionResponseDto crearParaAlumno(Long idAlumno, FormacionRequestDto datos);
    /**
     * Actualiza formación con los datos proporcionados.
     * @param id
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    FormacionResponseDto actualizar(Long id, FormacionRequestDto datos);
    /**
     * Elimina formación según el criterio indicado.
     * @param id
     */
    void eliminar(Long id);
}
