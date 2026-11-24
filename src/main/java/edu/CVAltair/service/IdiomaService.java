package edu.CVAltair.service;

import edu.CVAltair.dto.IdiomaRequestDto;
import edu.CVAltair.dto.IdiomaResponseDto;

import java.util.List;
import java.util.Optional;


/** Define el contrato de operaciones relacionado con idioma.
     *
     * @author ABR
     */
public interface IdiomaService {
    /**
     * Devuelve todos los registros de idiomas.
     * @return lista con los registros obtenidos.
     */
    List<IdiomaResponseDto> listarTodos();
    /**
     * Ejecuta la operación «listar por alumno» para idioma.
     * @param idAlumno
     * @return lista con los registros obtenidos.
     */
    List<IdiomaResponseDto> listarPorAlumno(Long idAlumno);
    /**
     * Busca idioma por nombre.
     * @param nombre
     * @return lista con los registros obtenidos.
     */
    List<IdiomaResponseDto> buscarPorNombre(String nombre);
    /**
     * Busca idioma por nombre y nivel.
     * @param nombre
     * @param nivel
     * @return lista con los registros obtenidos.
     */
    List<IdiomaResponseDto> buscarPorNombreYNivel(String nombre, String nivel);
    /**
     * Busca idioma por identificador.
     * @param id
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<IdiomaResponseDto> buscarPorId(Long id);
    /**
     * Crea idioma con los datos proporcionados.
     * @param idAlumno
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    IdiomaResponseDto crearParaAlumno(Long idAlumno, IdiomaRequestDto datos);
    /**
     * Actualiza idioma con los datos proporcionados.
     * @param id
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    IdiomaResponseDto actualizar(Long id, IdiomaRequestDto datos);
    /**
     * Elimina idioma según el criterio indicado.
     * @param id
     */
    void eliminar(Long id);
}
