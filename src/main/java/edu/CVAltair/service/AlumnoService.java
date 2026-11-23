package edu.CVAltair.service;

import edu.CVAltair.dto.AlumnoRequestDto;
import edu.CVAltair.dto.AlumnoResponseDto;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {
    List<AlumnoResponseDto> listarTodos();
    AlumnoResponseDto crear(AlumnoRequestDto crear);

    Optional<AlumnoResponseDto> buscarPorId(Long id);
    Optional<AlumnoResponseDto> buscarPorDni(String dni);
    Optional<AlumnoResponseDto> buscarPorEmail(String email);
    Optional<AlumnoResponseDto> buscarPorNumeroSeguridadSocial(String numeroSeguridadSocial);

    boolean existePorDni(String dni);
    boolean existePorEmail(String email);
    boolean existePorNumeroSeguridadSocial(String numeroSeguridadSocial);

    AlumnoResponseDto actualizarPorId(Long id, AlumnoRequestDto datosActalizados);
    AlumnoResponseDto actualizarPorDni(String dni, AlumnoRequestDto datosActalizados);

    void eliminarPorId(Long id);
    void eliminarPorDni (String dni);
}
