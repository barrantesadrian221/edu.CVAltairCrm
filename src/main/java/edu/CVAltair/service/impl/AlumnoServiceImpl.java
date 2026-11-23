package edu.CVAltair.service.impl;

import edu.CVAltair.dto.AlumnoRequestDto;
import edu.CVAltair.dto.AlumnoResponseDto;
import edu.CVAltair.entity.Alumno;
import edu.CVAltair.repository.AlumnoRepository;
import edu.CVAltair.service.AlumnoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {

        this.alumnoRepository = alumnoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoResponseDto> listarTodos() {
        return alumnoRepository.findAll()
                .stream().map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public AlumnoResponseDto crear(AlumnoRequestDto datos) {
        validarDatos(datos);
        validarClavesUnicas(null, datos);
        Alumno alumnoGuardado = alumnoRepository.save(mapToEntity(datos));
        return mapToResponseDto(alumnoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlumnoResponseDto> buscarPorId(Long id) {
        return alumnoRepository.findById(id).map(this::mapToResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlumnoResponseDto> buscarPorDni(String dni) {
        return alumnoRepository.findByDni(dni).map(this::mapToResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlumnoResponseDto> buscarPorEmail(String email) {
        return alumnoRepository.findByEmail(email).map(this::mapToResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlumnoResponseDto> buscarPorNumeroSeguridadSocial(String numeroSeguridadSocial) {
        return alumnoRepository.findByNumeroSeguridadSocial(numeroSeguridadSocial)
                .map(this::mapToResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorDni(String dni) {
        return alumnoRepository.existsByDni(dni);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorEmail(String email) {
        return alumnoRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorNumeroSeguridadSocial(String numeroSeguridadSocial) {
        return alumnoRepository.existsByNumeroSeguridadSocial(numeroSeguridadSocial);
    }

    @Override
    @Transactional
    public AlumnoResponseDto actualizarPorId(Long id, AlumnoRequestDto datosActualizados) {
        validarDatos(datosActualizados);
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con ID: " + id));
        validarClavesUnicas(alumno, datosActualizados);
        aplicarDatos(alumno, datosActualizados);
        return mapToResponseDto(alumnoRepository.save(alumno));
    }

    @Override
    @Transactional
    public AlumnoResponseDto actualizarPorDni(String dni, AlumnoRequestDto datosActualizados) {
        validarDatos(datosActualizados);
        Alumno alumno = alumnoRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con DNI: " + dni));
        validarClavesUnicas(alumno, datosActualizados);
        aplicarDatos(alumno, datosActualizados);
        return mapToResponseDto(alumnoRepository.save(alumno));
    }

    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con ID: " + id));
        alumnoRepository.delete(alumno);
    }

    @Override
    @Transactional
    public void eliminarPorDni(String dni) {
        Alumno alumno = alumnoRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con DNI: " + dni));
        alumnoRepository.delete(alumno);
    }

    private void validarDatos(AlumnoRequestDto datos) {
        if (datos == null) {
            throw new IllegalArgumentException("Los datos del alumno son obligatorios");
        }
        validarCampo(datos.nombre(), "nombre");
        validarCampo(datos.apellidos(), "apellidos");
        validarCampo(datos.dni(), "DNI");
        validarCampo(datos.email(), "email");
        validarCampo(datos.numeroSeguridadSocial(), "número de seguridad social");
        if (datos.fechaNacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
        }
    }

    private void validarCampo(String valor, String nombreCampo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " es obligatorio");
        }
    }

    private void validarClavesUnicas(Alumno existente, AlumnoRequestDto datos) {
        if ((existente == null || !existente.getDni().equalsIgnoreCase(datos.dni()))
                && alumnoRepository.existsByDni(datos.dni())) {
            throw new IllegalArgumentException("Ya existe un alumno registrado con el DNI: " + datos.dni());
        }
        if ((existente == null || !existente.getEmail().equalsIgnoreCase(datos.email()))
                && alumnoRepository.existsByEmail(datos.email())) {
            throw new IllegalArgumentException("Ya existe un alumno registrado con el Email: " + datos.email());
        }
        if ((existente == null || !existente.getNumeroSeguridadSocial().equals(datos.numeroSeguridadSocial()))
                && alumnoRepository.existsByNumeroSeguridadSocial(datos.numeroSeguridadSocial())) {
            throw new IllegalArgumentException(
                    "Ya existe un alumno registrado con el Nº de Seg. Social: "
                            + datos.numeroSeguridadSocial());
        }
    }

    private void aplicarDatos(Alumno alumno, AlumnoRequestDto datos) {
        alumno.setNombre(datos.nombre());
        alumno.setApellidos(datos.apellidos());
        alumno.setDni(datos.dni());
        alumno.setEmail(datos.email());
        alumno.setNumeroSeguridadSocial(datos.numeroSeguridadSocial());
        alumno.setFechaNacimiento(datos.fechaNacimiento());
        if (datos.tutorialCompletado() != null) {
            alumno.setTutorialCompletado(datos.tutorialCompletado());
        }
    }

    private AlumnoResponseDto mapToResponseDto(Alumno alumno) {
        return new AlumnoResponseDto(
                alumno.getIdAlumno(),
                alumno.getNombre(),
                alumno.getApellidos(),
                alumno.getDni(),
                alumno.getEmail(),
                alumno.getNumeroSeguridadSocial(),
                alumno.isTutorialCompletado(),
                alumno.getFechaNacimiento(),
                alumno.getCv() != null ? alumno.getCv().getIdCV() : null
        );
    }

    private Alumno mapToEntity(AlumnoRequestDto datos) {
        Alumno alumno = new Alumno();
        aplicarDatos(alumno, datos);
        if (datos.tutorialCompletado() == null) {
            alumno.setTutorialCompletado(false);
        }
        return alumno;
    }
}
