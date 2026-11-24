package edu.CVAltair.service;

import edu.CVAltair.dto.CvContenidoRequestDto;
import edu.CVAltair.dto.CvFiltroRequestDto;
import edu.CVAltair.dto.CvResponseDto;

import java.util.List;
import java.util.Optional;


/** Define el contrato de operaciones relacionado con currículum.
     *
     * @author ABR
     */
public interface CVService {

    /**
     * Crea currículum con los datos proporcionados.
     * @param idAlumno
     * @param contenido
     * @return resultado obtenido al ejecutar la operación.
     */
    CvResponseDto crearParaAlumno(Long idAlumno, CvContenidoRequestDto contenido);

    /**
     * Busca currículum por identificador.
     * @param id
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<CvResponseDto> buscarPorId(Long id);
    /**
     * Busca currículum por alumno.
     * @param idAlumno
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<CvResponseDto> buscarPorAlumno(Long idAlumno);


    /**
     * Actualiza currículum con los datos proporcionados.
     * @param id
     * @param contenido
     * @return resultado obtenido al ejecutar la operación.
     */
    CvResponseDto actualizarContenido(Long id, CvContenidoRequestDto contenido);

    /**
     * Elimina currículum según el criterio indicado.
     * @param id
     */
    void eliminar(Long id);


    /**
     * Ejecuta la operación «filtrar» para currículum.
     * @param filtros
     * @return lista con los registros obtenidos.
     */
    List<CvResponseDto> filtrar(CvFiltroRequestDto filtros);
}
