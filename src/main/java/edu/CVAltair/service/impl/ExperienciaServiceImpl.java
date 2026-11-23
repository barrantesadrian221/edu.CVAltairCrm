package edu.CVAltair.service.impl;

import edu.CVAltair.dto.ExperienciaRequestDto;
import edu.CVAltair.dto.ExperienciaResponseDto;
import edu.CVAltair.entity.Alumno;
import edu.CVAltair.entity.Experiencia;
import edu.CVAltair.repository.AlumnoRepository;
import edu.CVAltair.repository.ExperienciaRepository;
import edu.CVAltair.service.ExperienciaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienciaServiceImpl implements ExperienciaService {

    private final ExperienciaRepository experienciaRepository;
    private final AlumnoRepository alumnoRepository;

    public ExperienciaServiceImpl(
            ExperienciaRepository experienciaRepository,
            AlumnoRepository alumnoRepository) {
        this.experienciaRepository = experienciaRepository;
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExperienciaResponseDto> listarTodas() {
        return experienciaRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExperienciaResponseDto> listarPorAlumno(Long idAlumno) {
        if (!alumnoRepository.existsById(idAlumno)) {
            throw new EntityNotFoundException("Alumno no encontrado con ID: " + idAlumno);
        }
        return experienciaRepository.findByAlumnoIdAlumno(idAlumno)
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExperienciaResponseDto> buscarPorId(Long id) {
        return experienciaRepository.findById(id).map(this::mapToResponseDto);
    }

    @Override
    @Transactional
    public ExperienciaResponseDto crearParaAlumno(Long idAlumno, ExperienciaRequestDto datos) {
        validarDatos(datos);
        Alumno alumno = alumnoRepository.findById(idAlumno)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con ID: " + idAlumno));

        Experiencia experiencia = new Experiencia();
        aplicarDatos(experiencia, datos);
        experiencia.setAlumno(alumno);
        return mapToResponseDto(experienciaRepository.save(experiencia));
    }

    @Override
    @Transactional
    public ExperienciaResponseDto actualizar(Long id, ExperienciaRequestDto datos) {
        validarDatos(datos);
        Experiencia experiencia = experienciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Experiencia no encontrada con ID: " + id));
        aplicarDatos(experiencia, datos);
        return mapToResponseDto(experienciaRepository.save(experiencia));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Experiencia experiencia = experienciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Experiencia no encontrada con ID: " + id));
        experienciaRepository.delete(experiencia);
    }

    private void validarDatos(ExperienciaRequestDto datos) {
        if (datos == null) {
            throw new IllegalArgumentException("Los datos de la experiencia son obligatorios");
        }
        validarCampo(datos.puesto(), "puesto");
        validarCampo(datos.empresa(), "empresa");
        validarCampo(datos.periodo(), "periodo");
    }

    private void validarCampo(String valor, String nombreCampo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " es obligatorio");
        }
    }

    private void aplicarDatos(Experiencia experiencia, ExperienciaRequestDto datos) {
        experiencia.setPuesto(datos.puesto());
        experiencia.setEmpresa(datos.empresa());
        experiencia.setPeriodo(datos.periodo());
        experiencia.setDescripcion(datos.descripcion());
    }

    private ExperienciaResponseDto mapToResponseDto(Experiencia experiencia) {
        return new ExperienciaResponseDto(
                experiencia.getIdExperiencia(),
                experiencia.getPuesto(),
                experiencia.getEmpresa(),
                experiencia.getPeriodo(),
                experiencia.getDescripcion()
        );
    }
}
