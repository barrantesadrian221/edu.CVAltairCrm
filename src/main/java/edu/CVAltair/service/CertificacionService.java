package edu.CVAltair.service;

import edu.CVAltair.dto.CertificacionRequestDto;
import edu.CVAltair.dto.CertificacionResponseDto;

import java.util.List;
import java.util.Optional;


/** Define el contrato de operaciones relacionado con certificación.
     *
     * @author ABR
     */
public interface CertificacionService {
    /**
     * Ejecuta la operación «listar todas» para certificación.
     * @return lista con los registros obtenidos.
     */
    List<CertificacionResponseDto> listarTodas();
    /**
     * Ejecuta la operación «listar por alumno» para certificación.
     * @param idAlumno
     * @return lista con los registros obtenidos.
     */
    List<CertificacionResponseDto> listarPorAlumno(Long idAlumno);
    /**
     * Busca certificación por titulo.
     * @param texto
     * @return lista con los registros obtenidos.
     */
    List<CertificacionResponseDto> buscarPorTitulo(String texto);
    /**
     * Busca certificación por emision.
     * @param texto
     * @return lista con los registros obtenidos.
     */
    List<CertificacionResponseDto> buscarPorEmision(String texto);
    /**
     * Busca certificación por identificador.
     * @param id
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<CertificacionResponseDto> buscarPorId(Long id);
    /**
     * Crea certificación con los datos proporcionados.
     * @param idAlumno
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    CertificacionResponseDto crearParaAlumno(Long idAlumno, CertificacionRequestDto datos);
    /**
     * Actualiza certificación con los datos proporcionados.
     * @param id
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    CertificacionResponseDto actualizar(Long id, CertificacionRequestDto datos);
    /**
     * Elimina certificación según el criterio indicado.
     * @param id
     */
    void eliminar(Long id);
}
