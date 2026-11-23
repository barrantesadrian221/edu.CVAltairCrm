package edu.CVAltair.service;

import edu.CVAltair.dto.ExperienciaRequestDto;
import edu.CVAltair.dto.ExperienciaResponseDto;

import java.util.List;
import java.util.Optional;

public interface ExperienciaService {
    List<ExperienciaResponseDto> listarTodas();
    List<ExperienciaResponseDto> listarPorAlumno(Long idAlumno);
    Optional<ExperienciaResponseDto> buscarPorId(Long id);
    ExperienciaResponseDto crearParaAlumno(Long idAlumno, ExperienciaRequestDto datos);
    ExperienciaResponseDto actualizar(Long id, ExperienciaRequestDto datos);
    void eliminar(Long id);
}
