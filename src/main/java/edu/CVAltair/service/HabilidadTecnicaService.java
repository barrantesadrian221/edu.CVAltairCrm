package edu.CVAltair.service;

import edu.CVAltair.dto.HabilidadTecnicaRequestDto;
import edu.CVAltair.dto.HabilidadTecnicaResponseDto;

import java.util.List;
import java.util.Optional;

public interface HabilidadTecnicaService {
    List<HabilidadTecnicaResponseDto> listarTodas();
    List<HabilidadTecnicaResponseDto> listarPorAlumno(Long idAlumno);
    List<HabilidadTecnicaResponseDto> buscarPorNombre(String nombre);
    List<HabilidadTecnicaResponseDto> listarPorDestacada(boolean destacada);
    Optional<HabilidadTecnicaResponseDto> buscarPorId(Long id);
    HabilidadTecnicaResponseDto crearParaAlumno(Long idAlumno, HabilidadTecnicaRequestDto datos);
    HabilidadTecnicaResponseDto actualizar(Long id, HabilidadTecnicaRequestDto datos);
    void eliminar(Long id);
}
