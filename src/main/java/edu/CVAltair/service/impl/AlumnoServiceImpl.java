package edu.CVAltair.service.impl;

import edu.CVAltair.dto.AlumnoRequestDto;
import edu.CVAltair.dto.AlumnoResponseDto;
import edu.CVAltair.dto.EstadoTutorial;
import edu.CVAltair.dto.TutorialPasoDto;
import edu.CVAltair.dto.TutorialResponseDto;
import edu.CVAltair.entity.Alumno;
import edu.CVAltair.repository.AlumnoRepository;
import edu.CVAltair.repository.CVRepository;
import edu.CVAltair.service.AlumnoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * Implementación de los servicios de alumno.

 * @author ABR
 */
@Service
public class AlumnoServiceImpl implements AlumnoService {

    /**
     * Pasos y rutas que la interfaz muestra al iniciar el tutorial.
     * Implementado con ayuda de IA.
     */
    private static final List<TutorialPasoDto> PASOS_TUTORIAL = List.of(
            new TutorialPasoDto(
                    1,
                    "Completa tus datos personales",
                    "Consulta o actualiza el perfil del alumno antes de preparar el CV.",
                    "GET / PUT",
                    "/api/alumnos/{id}"
            ),
            new TutorialPasoDto(
                    2,
                    "Registra experiencia laboral",
                    "Crea las experiencias del alumno; después podrás elegir cuáles incluir en el CV.",
                    "GET / POST",
                    "/api/experiencias/alumno/{idAlumno}"
            ),
            new TutorialPasoDto(
                    3,
                    "Añade formación",
                    "Registra estudios y formación, indicando si siguen en curso.",
                    "GET / POST",
                    "/api/formaciones/alumno/{idAlumno}"
            ),
            new TutorialPasoDto(
                    4,
                    "Añade aptitudes",
                    "Guarda habilidades técnicas y marca las que quieras destacar.",
                    "GET / POST",
                    "/api/habilidades/alumno/{idAlumno}"
            ),
            new TutorialPasoDto(
                    5,
                    "Añade idiomas y certificaciones",
                    "Registra el nivel de cada idioma y las certificaciones obtenidas.",
                    "GET / POST",
                    "/api/idiomas/alumno/{idAlumno} y /api/certificaciones/alumno/{idAlumno}"
            ),
            new TutorialPasoDto(
                    6,
                    "Monta y ordena el CV",
                    "Selecciona los IDs de tus elementos y envía ordenBloques para definir el orden. Puedes sustituir el contenido más tarde.",
                    "POST / PUT",
                    "/api/cvs/alumno/{idAlumno} y /api/cvs/{id}"
            )
    );

    private final AlumnoRepository alumnoRepository;
    private final CVRepository cvRepository;

    /**
     * Crea una instancia de alumno.
     * @param alumnoRepository
     * @param cvRepository
     */
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository, CVRepository cvRepository) {
        this.alumnoRepository = alumnoRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoResponseDto> listarTodos() {
        return alumnoRepository.findAll()
                .stream().map(this::mapToResponseDto)
                .toList();
    }

    /**
     * Crea alumno con los datos proporcionados.
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    @Override
    @Transactional
    public AlumnoResponseDto crear(AlumnoRequestDto datos) {
        validarDatos(datos);
        validarClavesUnicas(null, datos);
        Alumno alumnoGuardado = alumnoRepository.save(mapToEntity(datos));
        return mapToResponseDto(alumnoGuardado);
    }
    //METODOS DE BUSQUEDA
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

    /**
     *
     * @param dni
     * @return
     */
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

    /**
     * Actualiza alumno con los datos proporcionados.
     * @param id
     * @param datosActualizados
     * @return resultado obtenido al ejecutar la operación.
     */
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

    /**
     * Actualiza alumno con los datos proporcionados.
     * @param dni
     * @param datosActualizados
     * @return resultado obtenido al ejecutar la operación.
     */
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
    @Transactional(readOnly = true)
    public TutorialResponseDto consultarTutorial(Long id) {
        return mapToTutorialResponse(buscarAlumno(id));
    }

    /**
     * Marca el tutorial del alumno como completado y devuelve su estado actualizado.
     * @param id
     * @return estado actualizado y pasos disponibles del tutorial.
     */
    @Override
    @Transactional
    public TutorialResponseDto completarTutorial(Long id) {
        Alumno alumno = buscarAlumno(id);
        alumno.setTutorialCompletado(true);
        alumno.setTutorialOmitido(false);
        return mapToTutorialResponse(alumnoRepository.save(alumno));
    }

    /**
     * Marca el tutorial del alumno como omitido y devuelve su estado actualizado.
     * @param id
     * @return estado actualizado y pasos disponibles del tutorial.
     */
    @Override
    @Transactional
    public TutorialResponseDto omitirTutorial(Long id) {
        Alumno alumno = buscarAlumno(id);
        alumno.setTutorialCompletado(false);
        alumno.setTutorialOmitido(true);
        return mapToTutorialResponse(alumnoRepository.save(alumno));
    }

    /**
     * Elimina alumno según el criterio indicado.
     * @param id
     */
    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con ID: " + id));
        eliminarCvAntesDelAlumno(alumno);
        alumnoRepository.delete(alumno);
    }

    /**
     * Elimina alumno según el criterio indicado.
     * @param dni
     */
    @Override
    @Transactional
    public void eliminarPorDni(String dni) {
        Alumno alumno = alumnoRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con DNI: " + dni));
        eliminarCvAntesDelAlumno(alumno);
        alumnoRepository.delete(alumno);
    }

    private void eliminarCvAntesDelAlumno(Alumno alumno) {
        cvRepository.findByAlumnoIdAlumno(alumno.getIdAlumno()).ifPresent(cv -> {
            alumno.setCv(null);
            cvRepository.delete(cv);
            cvRepository.flush();
        });
    }

    /**
     * Ejecuta la operación «validar datos» para alumno.
     * @param datos
     * @throws IllegalArgumentException si se produce una excepción de tipo {@link IllegalArgumentException} durante la operación.
     */
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

    /**
     * Ejecuta la operación «validar campo» para alumno.
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
     * Ejecuta la operación «validar claves unicas» para alumno.
     * @param existente
     * @param datos
     * @throws IllegalArgumentException si se produce una excepción de tipo {@link IllegalArgumentException} durante la operación.
     */
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

    /**
     * Ejecuta la operación «aplicar datos» para alumno.
     * @param alumno
     * @param datos
     */
    private void aplicarDatos(Alumno alumno, AlumnoRequestDto datos) {
        alumno.setNombre(datos.nombre());
        alumno.setApellidos(datos.apellidos());
        alumno.setDni(datos.dni());
        alumno.setEmail(datos.email());
        alumno.setNumeroSeguridadSocial(datos.numeroSeguridadSocial());
        alumno.setFechaNacimiento(datos.fechaNacimiento());
        if (datos.tutorialCompletado() != null) {
            alumno.setTutorialCompletado(datos.tutorialCompletado());
            alumno.setTutorialOmitido(false);
        }
    }

    /** Busca al alumno o comunica que no existe. Implementado con ayuda de IA. */
    private Alumno buscarAlumno(Long id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con ID: " + id));
    }

    /** Convierte los campos guardados en la respuesta del tutorial. Implementado con ayuda de IA. */
    private TutorialResponseDto mapToTutorialResponse(Alumno alumno) {
        EstadoTutorial estado = alumno.isTutorialOmitido()
                ? EstadoTutorial.OMITIDO
                : alumno.isTutorialCompletado()
                        ? EstadoTutorial.COMPLETADO
                        : EstadoTutorial.PENDIENTE;
        return new TutorialResponseDto(
                alumno.getIdAlumno(),
                estado,
                estado == EstadoTutorial.PENDIENTE,
                PASOS_TUTORIAL
        );
    }

    /**
     * Ejecuta la operación «map to respuesta DTO» para alumno.
     * @param alumno
     * @return resultado obtenido al ejecutar la operación.
     */
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

    /**
     * Ejecuta la operación «map to entity» para alumno.
     * @param datos
     * @return resultado obtenido al ejecutar la operación.
     */
    private Alumno mapToEntity(AlumnoRequestDto datos) {
        Alumno alumno = new Alumno();
        aplicarDatos(alumno, datos);
        if (datos.tutorialCompletado() == null) {
            alumno.setTutorialCompletado(false);
        }
        return alumno;
    }
}
