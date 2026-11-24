package edu.CVAltair.service.impl;

import edu.CVAltair.dto.IdiomaRequestDto;
import edu.CVAltair.dto.IdiomaResponseDto;
import edu.CVAltair.entity.Alumno;
import edu.CVAltair.entity.Idioma;
import edu.CVAltair.repository.AlumnoRepository;
import edu.CVAltair.repository.IdiomaRepository;
import edu.CVAltair.service.IdiomaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * Implementación de los servicios de idioma.

 * @author ABR
 */
@Service
public class IdiomaServiceImpl implements IdiomaService {

    private final IdiomaRepository idiomaRepository;
    private final AlumnoRepository alumnoRepository;

    /**
     * Crea una instancia de idioma.
     * @param idiomaRepository
     * @param alumnoRepository
     */
    public IdiomaServiceImpl(IdiomaRepository idiomaRepository, AlumnoRepository alumnoRepository) {
        this.idiomaRepository = idiomaRepository;
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IdiomaResponseDto> listarTodos() {
        return idiomaRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<IdiomaResponseDto> listarPorAlumno(Long idAlumno) {
        if (!alumnoRepository.existsById(idAlumno)) {
            throw new EntityNotFoundException("Alumno no encontrado con ID: " + idAlumno);
        }
        return idiomaRepository.findByAlumnoIdAlumno(idAlumno)
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<IdiomaResponseDto> buscarPorNombre(String nombre) {
        validarCampo(nombre, "nombre");
        return idiomaRepository.findByNombreContainingIgnoreCase(nombre.trim())
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<IdiomaResponseDto> buscarPorNombreYNivel(String nombre, String nivel) {
        validarCampo(nombre, "nombre");
        validarCampo(nivel, "nivel");
        return idiomaRepository.findByNombreIgnoreCaseAndNivelIgnoreCase(nombre.trim(), nivel.trim())
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IdiomaResponseDto> buscarPorId(Long id) {
        return idiomaRepository.findById(id).map(this::mapToResponseDto);
    }

    /**
     * Crea idioma con los datos proporcionados.
     * @param idAlumno
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    @Override
    @Transactional
    public IdiomaResponseDto crearParaAlumno(Long idAlumno, IdiomaRequestDto datos) {
        validarDatos(datos);
        Alumno alumno = alumnoRepository.findById(idAlumno)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con ID: " + idAlumno));

        Idioma idioma = new Idioma();
        aplicarDatos(idioma, datos);
        idioma.setAlumno(alumno);
        return mapToResponseDto(idiomaRepository.save(idioma));
    }

    /**
     * Actualiza idioma con los datos proporcionados.
     * @param id
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    @Override
    @Transactional
    public IdiomaResponseDto actualizar(Long id, IdiomaRequestDto datos) {
        validarDatos(datos);
        Idioma idioma = idiomaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Idioma no encontrado con ID: " + id));
        aplicarDatos(idioma, datos);
        return mapToResponseDto(idiomaRepository.save(idioma));
    }

    /**
     * Elimina idioma según el criterio indicado.
     * @param id
     */
    @Override
    @Transactional
    public void eliminar(Long id) {
        Idioma idioma = idiomaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Idioma no encontrado con ID: " + id));
        idiomaRepository.delete(idioma);
    }

    /**
     * Ejecuta la operación «validar datos» para idioma.
     * @param datos
     * @throws IllegalArgumentException si se produce una excepción de tipo {@link IllegalArgumentException} durante la operación.
     */
    private void validarDatos(IdiomaRequestDto datos) {
        if (datos == null) {
            throw new IllegalArgumentException("Los datos del idioma son obligatorios");
        }
        validarCampo(datos.nombre(), "nombre");
        validarCampo(datos.nivel(), "nivel");
    }

    /**
     * Ejecuta la operación «validar campo» para idioma.
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
     * Ejecuta la operación «aplicar datos» para idioma.
     * @param idioma
     * @param datos
     */
    private void aplicarDatos(Idioma idioma, IdiomaRequestDto datos) {
        idioma.setNombre(datos.nombre());
        idioma.setNivel(datos.nivel());
    }

    /**
     * Ejecuta la operación «map to respuesta DTO» para idioma.
     * @param idioma
     * @return resultado obtenido al ejecutar la operación.
     */
    private IdiomaResponseDto mapToResponseDto(Idioma idioma) {
        return new IdiomaResponseDto(
                idioma.getIdIdioma(),
                idioma.getNombre(),
                idioma.getNivel()
        );
    }
}
