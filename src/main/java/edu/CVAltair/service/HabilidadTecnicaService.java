package edu.CVAltair.service;

import edu.CVAltair.dto.HabilidadTecnicaRequestDto;
import edu.CVAltair.dto.HabilidadTecnicaResponseDto;

import java.util.List;
import java.util.Optional;


/** Define el contrato de operaciones relacionado con habilidad técnica.
     *
     * @author ABR
     */
public interface HabilidadTecnicaService {
    /**
     * Ejecuta la operación «listar todas» para habilidad técnica.
     * @return lista con los registros obtenidos.
     */
    List<HabilidadTecnicaResponseDto> listarTodas();
    /**
     * Ejecuta la operación «listar por alumno» para habilidad técnica.
     * @param idAlumno
     * @return lista con los registros obtenidos.
     */
    List<HabilidadTecnicaResponseDto> listarPorAlumno(Long idAlumno);
    /**
     * Busca habilidad técnica por nombre.
     * @param nombre
     * @return lista con los registros obtenidos.
     */
    List<HabilidadTecnicaResponseDto> buscarPorNombre(String nombre);
    /**
     * Ejecuta la operación «listar por destacada» para habilidad técnica.
     * @param destacada
     * @return lista con los registros obtenidos.
     */
    List<HabilidadTecnicaResponseDto> listarPorDestacada(boolean destacada);
    /**
     * Busca habilidad técnica por identificador.
     * @param id
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<HabilidadTecnicaResponseDto> buscarPorId(Long id);
    /**
     * Crea habilidad técnica con los datos proporcionados.
     * @param idAlumno
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    HabilidadTecnicaResponseDto crearParaAlumno(Long idAlumno, HabilidadTecnicaRequestDto datos);
    /**
     * Actualiza habilidad técnica con los datos proporcionados.
     * @param id
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    HabilidadTecnicaResponseDto actualizar(Long id, HabilidadTecnicaRequestDto datos);
    /**
     * Elimina habilidad técnica según el criterio indicado.
     * @param id
     */
    void eliminar(Long id);
}
