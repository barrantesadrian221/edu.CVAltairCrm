package edu.CVAltair.service.impl;

import edu.CVAltair.dto.HabilidadTecnicaRequestDto;
import edu.CVAltair.dto.HabilidadTecnicaResponseDto;
import edu.CVAltair.entity.Alumno;
import edu.CVAltair.entity.HabilidadTecnica;
import edu.CVAltair.repository.AlumnoRepository;
import edu.CVAltair.repository.HabilidadTecnicaRepository;
import edu.CVAltair.service.HabilidadTecnicaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadTecnicaServiceImpl implements HabilidadTecnicaService {

    private final HabilidadTecnicaRepository habilidadRepository;
    private final AlumnoRepository alumnoRepository;

    public HabilidadTecnicaServiceImpl(
            HabilidadTecnicaRepository habilidadRepository,
            AlumnoRepository alumnoRepository) {
        this.habilidadRepository = habilidadRepository;
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabilidadTecnicaResponseDto> listarTodas() {
        return habilidadRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabilidadTecnicaResponseDto> listarPorAlumno(Long idAlumno) {
        if (!alumnoRepository.existsById(idAlumno)) {
            throw new EntityNotFoundException("Alumno no encontrado con ID: " + idAlumno);
        }
        return habilidadRepository.findByAlumnoIdAlumno(idAlumno)
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabilidadTecnicaResponseDto> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la habilidad es obligatorio");
        }
        return habilidadRepository.findByNombreContainingIgnoreCase(nombre.trim())
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabilidadTecnicaResponseDto> listarPorDestacada(boolean destacada) {
        return habilidadRepository.findByDestacada(destacada)
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HabilidadTecnicaResponseDto> buscarPorId(Long id) {
        return habilidadRepository.findById(id).map(this::mapToResponseDto);
    }

    @Override
    @Transactional
    public HabilidadTecnicaResponseDto crearParaAlumno(
            Long idAlumno,
            HabilidadTecnicaRequestDto datos) {
        validarDatos(datos);
        Alumno alumno = alumnoRepository.findById(idAlumno)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con ID: " + idAlumno));

        HabilidadTecnica habilidad = new HabilidadTecnica();
        aplicarDatos(habilidad, datos);
        habilidad.setAlumno(alumno);
        return mapToResponseDto(habilidadRepository.save(habilidad));
    }

    @Override
    @Transactional
    public HabilidadTecnicaResponseDto actualizar(Long id, HabilidadTecnicaRequestDto datos) {
        validarDatos(datos);
        HabilidadTecnica habilidad = habilidadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con ID: " + id));
        aplicarDatos(habilidad, datos);
        return mapToResponseDto(habilidadRepository.save(habilidad));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        HabilidadTecnica habilidad = habilidadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con ID: " + id));
        habilidadRepository.delete(habilidad);
    }

    private void validarDatos(HabilidadTecnicaRequestDto datos) {
        if (datos == null) {
            throw new IllegalArgumentException("Los datos de la habilidad son obligatorios");
        }
        if (datos.nombre() == null || datos.nombre().isBlank()) {
            throw new IllegalArgumentException("El nombre de la habilidad es obligatorio");
        }
    }

    private void aplicarDatos(HabilidadTecnica habilidad, HabilidadTecnicaRequestDto datos) {
        habilidad.setNombre(datos.nombre());
        habilidad.setDescripcion(datos.descripcion());
        habilidad.setDestacada(datos.destacada());
    }

    private HabilidadTecnicaResponseDto mapToResponseDto(HabilidadTecnica habilidad) {
        return new HabilidadTecnicaResponseDto(
                habilidad.getIdHabilidadTecnica(),
                habilidad.getNombre(),
                habilidad.getDescripcion(),
                habilidad.isDestacada()
        );
    }
}
