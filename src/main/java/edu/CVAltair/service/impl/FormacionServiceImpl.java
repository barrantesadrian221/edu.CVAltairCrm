package edu.CVAltair.service.impl;

import edu.CVAltair.dto.FormacionRequestDto;
import edu.CVAltair.dto.FormacionResponseDto;
import edu.CVAltair.entity.Alumno;
import edu.CVAltair.entity.Formacion;
import edu.CVAltair.repository.AlumnoRepository;
import edu.CVAltair.repository.FormacionRepository;
import edu.CVAltair.service.FormacionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FormacionServiceImpl implements FormacionService {

    private final FormacionRepository formacionRepository;
    private final AlumnoRepository alumnoRepository;

    public FormacionServiceImpl(
            FormacionRepository formacionRepository,
            AlumnoRepository alumnoRepository) {
        this.formacionRepository = formacionRepository;
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormacionResponseDto> listarTodas() {
        return formacionRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormacionResponseDto> listarPorAlumno(Long idAlumno) {
        if (!alumnoRepository.existsById(idAlumno)) {
            throw new EntityNotFoundException("Alumno no encontrado con ID: " + idAlumno);
        }
        return formacionRepository.findByAlumnoIdAlumno(idAlumno)
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FormacionResponseDto> buscarPorId(Long id) {
        return formacionRepository.findById(id).map(this::mapToResponseDto);
    }

    @Override
    @Transactional
    public FormacionResponseDto crearParaAlumno(Long idAlumno, FormacionRequestDto datos) {
        validarDatos(datos);
        Alumno alumno = alumnoRepository.findById(idAlumno)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con ID: " + idAlumno));

        Formacion formacion = new Formacion();
        aplicarDatos(formacion, datos);
        formacion.setAlumno(alumno);
        return mapToResponseDto(formacionRepository.save(formacion));
    }

    @Override
    @Transactional
    public FormacionResponseDto actualizar(Long id, FormacionRequestDto datos) {
        validarDatos(datos);
        Formacion formacion = formacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formación no encontrada con ID: " + id));
        aplicarDatos(formacion, datos);
        return mapToResponseDto(formacionRepository.save(formacion));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Formacion formacion = formacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formación no encontrada con ID: " + id));
        formacionRepository.delete(formacion);
    }

    private void validarDatos(FormacionRequestDto datos) {
        if (datos == null) {
            throw new IllegalArgumentException("Los datos de la formación son obligatorios");
        }
        validarCampo(datos.titulo(), "título");
        validarCampo(datos.centro(), "centro");
        validarCampo(datos.periodo(), "periodo");
    }

    private void validarCampo(String valor, String nombreCampo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " es obligatorio");
        }
    }

    private void aplicarDatos(Formacion formacion, FormacionRequestDto datos) {
        formacion.setTitulo(datos.titulo());
        formacion.setCentro(datos.centro());
        formacion.setPeriodo(datos.periodo());
        formacion.setEnCurso(datos.enCurso());
    }

    private FormacionResponseDto mapToResponseDto(Formacion formacion) {
        return new FormacionResponseDto(
                formacion.getIdFormacion(),
                formacion.getTitulo(),
                formacion.getCentro(),
                formacion.getPeriodo(),
                formacion.isEnCurso()
        );
    }
}
