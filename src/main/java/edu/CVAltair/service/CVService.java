package edu.CVAltair.service;

import edu.CVAltair.dto.CvContenidoRequestDto;
import edu.CVAltair.dto.CvFiltroRequestDto;
import edu.CVAltair.dto.CvResponseDto;

import java.util.List;
import java.util.Optional;

public interface CVService {
    CvResponseDto crearParaAlumno(Long idAlumno, CvContenidoRequestDto contenido);
    Optional<CvResponseDto> buscarPorId(Long id);
    Optional<CvResponseDto> buscarPorAlumno(Long idAlumno);
    CvResponseDto actualizarContenido(Long id, CvContenidoRequestDto contenido);
    void eliminar(Long id);
    List<CvResponseDto> filtrar(CvFiltroRequestDto filtros);
}
