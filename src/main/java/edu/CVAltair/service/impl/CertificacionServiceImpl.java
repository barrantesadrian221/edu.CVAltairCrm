package edu.CVAltair.service.impl;

import edu.CVAltair.dto.CertificacionRequestDto;
import edu.CVAltair.dto.CertificacionResponseDto;
import edu.CVAltair.entity.Alumno;
import edu.CVAltair.entity.Certificacion;
import edu.CVAltair.repository.AlumnoRepository;
import edu.CVAltair.repository.CertificacionRepository;
import edu.CVAltair.service.CertificacionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * Implementación de los servicios de certificación.

 * @author ABR
 */
@Service
public class CertificacionServiceImpl implements CertificacionService {

    private final CertificacionRepository certificacionRepository;
    private final AlumnoRepository alumnoRepository;

    /**
     * Crea una instancia de certificación.
     * @param certificacionRepository
     * @param alumnoRepository
     */
    public CertificacionServiceImpl(
            CertificacionRepository certificacionRepository,
            AlumnoRepository alumnoRepository) {
        this.certificacionRepository = certificacionRepository;
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CertificacionResponseDto> listarTodas() {
        return certificacionRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CertificacionResponseDto> listarPorAlumno(Long idAlumno) {
        if (!alumnoRepository.existsById(idAlumno)) {
            throw new EntityNotFoundException("Alumno no encontrado con ID: " + idAlumno);
        }
        return certificacionRepository.findByAlumnoIdAlumno(idAlumno)
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CertificacionResponseDto> buscarPorTitulo(String texto) {
        validarTextoBusqueda(texto, "título");
        return certificacionRepository.findByTituloContainingIgnoreCase(texto.trim())
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CertificacionResponseDto> buscarPorEmision(String texto) {
        validarTextoBusqueda(texto, "entidad emisora");
        return certificacionRepository.findByEmisionContainingIgnoreCase(texto.trim())
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CertificacionResponseDto> buscarPorId(Long id) {
        return certificacionRepository.findById(id).map(this::mapToResponseDto);
    }

    /**
     * Crea certificación con los datos proporcionados.
     * @param idAlumno
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    @Override
    @Transactional
    public CertificacionResponseDto crearParaAlumno(Long idAlumno, CertificacionRequestDto datos) {
        validarDatos(datos);
        Alumno alumno = alumnoRepository.findById(idAlumno)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con ID: " + idAlumno));

        Certificacion certificacion = new Certificacion();
        aplicarDatos(certificacion, datos);
        certificacion.setAlumno(alumno);
        return mapToResponseDto(certificacionRepository.save(certificacion));
    }

    /**
     * Actualiza certificación con los datos proporcionados.
     * @param id
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    @Override
    @Transactional
    public CertificacionResponseDto actualizar(Long id, CertificacionRequestDto datos) {
        validarDatos(datos);
        Certificacion certificacion = certificacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificación no encontrada con ID: " + id));
        aplicarDatos(certificacion, datos);
        return mapToResponseDto(certificacionRepository.save(certificacion));
    }

    /**
     * Elimina certificación según el criterio indicado.
     * @param id
     */
    @Override
    @Transactional
    public void eliminar(Long id) {
        Certificacion certificacion = certificacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificación no encontrada con ID: " + id));
        certificacionRepository.delete(certificacion);
    }

    /**
     * Ejecuta la operación «validar datos» para certificación.
     * @param datos
     * @throws IllegalArgumentException si se produce una excepción de tipo {@link IllegalArgumentException} durante la operación.
     */
    private void validarDatos(CertificacionRequestDto datos) {
        if (datos == null) {
            throw new IllegalArgumentException("Los datos de la certificación son obligatorios");
        }
        validarCampo(datos.titulo(), "título");
        validarCampo(datos.emision(), "entidad emisora");
        if (datos.fechaObtencion() == null) {
            throw new IllegalArgumentException("La fecha de obtención es obligatoria");
        }
    }

    /**
     * Ejecuta la operación «validar texto busqueda» para certificación.
     * @param texto
     * @param campo
     * @throws IllegalArgumentException si se produce una excepción de tipo {@link IllegalArgumentException} durante la operación.
     */
    private void validarTextoBusqueda(String texto, String campo) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("El texto de búsqueda para " + campo + " es obligatorio");
        }
    }

    /**
     * Ejecuta la operación «validar campo» para certificación.
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
     * Ejecuta la operación «aplicar datos» para certificación.
     * @param certificacion
     * @param datos
     */
    private void aplicarDatos(Certificacion certificacion, CertificacionRequestDto datos) {
        certificacion.setTitulo(datos.titulo());
        certificacion.setEmision(datos.emision());
        certificacion.setFechaObtencion(datos.fechaObtencion());
    }

    /**
     * Ejecuta la operación «map to respuesta DTO» para certificación.
     * @param certificacion
     * @return resultado obtenido al ejecutar la operación.
     */
    private CertificacionResponseDto mapToResponseDto(Certificacion certificacion) {
        return new CertificacionResponseDto(
                certificacion.getIdCertificacion(),
                certificacion.getTitulo(),
                certificacion.getEmision(),
                certificacion.getFechaObtencion()
        );
    }
}
