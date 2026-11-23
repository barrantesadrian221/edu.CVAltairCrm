package edu.CVAltair.service;

import edu.CVAltair.dto.CertificacionRequestDto;
import edu.CVAltair.dto.CertificacionResponseDto;

import java.util.List;
import java.util.Optional;

public interface CertificacionService {
    List<CertificacionResponseDto> listarTodas();
    List<CertificacionResponseDto> listarPorAlumno(Long idAlumno);
    List<CertificacionResponseDto> buscarPorTitulo(String texto);
    List<CertificacionResponseDto> buscarPorEmision(String texto);
    Optional<CertificacionResponseDto> buscarPorId(Long id);
    CertificacionResponseDto crearParaAlumno(Long idAlumno, CertificacionRequestDto datos);
    CertificacionResponseDto actualizar(Long id, CertificacionRequestDto datos);
    void eliminar(Long id);
}
