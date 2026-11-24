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


/**
 * Implementación de los servicios de experiencia.

 * @author ABR
 */
@Service
public class ExperienciaServiceImpl implements ExperienciaService {

    private final ExperienciaRepository experienciaRepository;
    private final AlumnoRepository alumnoRepository;

    /**
     * Crea una instancia de experiencia.
     * @param experienciaRepository
     * @param alumnoRepository
     */
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

    /**
     * Crea experiencia con los datos proporcionados.
     * @param idAlumno
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
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

    /**
     * Actualiza experiencia con los datos proporcionados.
     * @param id
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    @Override
    @Transactional
    public ExperienciaResponseDto actualizar(Long id, ExperienciaRequestDto datos) {
        validarDatos(datos);
        Experiencia experiencia = experienciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Experiencia no encontrada con ID: " + id));
        aplicarDatos(experiencia, datos);
        return mapToResponseDto(experienciaRepository.save(experiencia));
    }

    /**
     * Elimina experiencia según el criterio indicado.
     * @param id
     */
    @Override
    @Transactional
    public void eliminar(Long id) {
        Experiencia experiencia = experienciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Experiencia no encontrada con ID: " + id));
        experienciaRepository.delete(experiencia);
    }

    /**
     * Ejecuta la operación «validar datos» para experiencia.
     * @param datos
     * @throws IllegalArgumentException si se produce una excepción de tipo {@link IllegalArgumentException} durante la operación.
     */
    private void validarDatos(ExperienciaRequestDto datos) {
        if (datos == null) {
            throw new IllegalArgumentException("Los datos de la experiencia son obligatorios");
        }
        validarCampo(datos.puesto(), "puesto");
        validarCampo(datos.empresa(), "empresa");
        validarCampo(datos.periodo(), "periodo");
    }

    /**
     * Ejecuta la operación «validar campo» para experiencia.
     * @param valor
     * @param nombreCampo
     * @throws IllegalArgumentException si se produce una excepción de tipo {@link IllegalArgumentException} durante la operación.
     */
    private void validarCampo(String valor, String nombreCampo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " es obligatorio");
        }
    }

    /**
     * Ejecuta la operación «aplicar datos» para experiencia.
     * @param experiencia
     * @param datos
     */
    private void aplicarDatos(Experiencia experiencia, ExperienciaRequestDto datos) {
        experiencia.setPuesto(datos.puesto());
        experiencia.setEmpresa(datos.empresa());
        experiencia.setPeriodo(datos.periodo());
        experiencia.setDescripcion(datos.descripcion());
    }

    /**
     * Ejecuta la operación «map to respuesta DTO» para experiencia.
     * @param experiencia
     * @return resultado obtenido al ejecutar la operación.
     */
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
