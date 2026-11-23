package edu.CVAltair.service;

import edu.CVAltair.dto.FormacionRequestDto;
import edu.CVAltair.dto.FormacionResponseDto;

import java.util.List;
import java.util.Optional;

public interface FormacionService {
    List<FormacionResponseDto> listarTodas();
    List<FormacionResponseDto> listarPorAlumno(Long idAlumno);
    Optional<FormacionResponseDto> buscarPorId(Long id);
    FormacionResponseDto crearParaAlumno(Long idAlumno, FormacionRequestDto datos);
    FormacionResponseDto actualizar(Long id, FormacionRequestDto datos);
    void eliminar(Long id);
}
