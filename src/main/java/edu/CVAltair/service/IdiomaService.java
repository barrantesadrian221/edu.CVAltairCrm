package edu.CVAltair.service;

import edu.CVAltair.dto.IdiomaRequestDto;
import edu.CVAltair.dto.IdiomaResponseDto;

import java.util.List;
import java.util.Optional;

public interface IdiomaService {
    List<IdiomaResponseDto> listarTodos();
    List<IdiomaResponseDto> listarPorAlumno(Long idAlumno);
    List<IdiomaResponseDto> buscarPorNombre(String nombre);
    List<IdiomaResponseDto> buscarPorNombreYNivel(String nombre, String nivel);
    Optional<IdiomaResponseDto> buscarPorId(Long id);
    IdiomaResponseDto crearParaAlumno(Long idAlumno, IdiomaRequestDto datos);
    IdiomaResponseDto actualizar(Long id, IdiomaRequestDto datos);
    void eliminar(Long id);
}
