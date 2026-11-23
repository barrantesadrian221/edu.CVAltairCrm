package edu.CVAltair.service.impl;

import edu.CVAltair.dto.AlumnoResponseDto;
import edu.CVAltair.dto.CertificacionResponseDto;
import edu.CVAltair.dto.CvBloqueRequestDto;
import edu.CVAltair.dto.CvBloqueResponseDto;
import edu.CVAltair.dto.CvContenidoRequestDto;
import edu.CVAltair.dto.CvFiltroRequestDto;
import edu.CVAltair.dto.CvResponseDto;
import edu.CVAltair.dto.ExperienciaResponseDto;
import edu.CVAltair.dto.FormacionResponseDto;
import edu.CVAltair.dto.HabilidadTecnicaResponseDto;
import edu.CVAltair.dto.IdiomaResponseDto;
import edu.CVAltair.dto.TipoBloqueCv;
import edu.CVAltair.entity.Alumno;
import edu.CVAltair.entity.CV;
import edu.CVAltair.entity.Certificacion;
import edu.CVAltair.entity.CvCertificacion;
import edu.CVAltair.entity.CvExperiencia;
import edu.CVAltair.entity.CvFormacion;
import edu.CVAltair.entity.CvHabilidad;
import edu.CVAltair.entity.CvIdioma;
import edu.CVAltair.entity.Experiencia;
import edu.CVAltair.entity.Formacion;
import edu.CVAltair.entity.HabilidadTecnica;
import edu.CVAltair.entity.Idioma;
import edu.CVAltair.repository.AlumnoRepository;
import edu.CVAltair.repository.CVRepository;
import edu.CVAltair.repository.CertificacionRepository;
import edu.CVAltair.repository.ExperienciaRepository;
import edu.CVAltair.repository.FormacionRepository;
import edu.CVAltair.repository.HabilidadTecnicaRepository;
import edu.CVAltair.repository.IdiomaRepository;
import edu.CVAltair.service.CVService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;

@Service
public class CVServiceImpl implements CVService {

    private final CVRepository cvRepository;
    private final AlumnoRepository alumnoRepository;
    private final ExperienciaRepository experienciaRepository;
    private final FormacionRepository formacionRepository;
    private final HabilidadTecnicaRepository habilidadRepository;
    private final IdiomaRepository idiomaRepository;
    private final CertificacionRepository certificacionRepository;

    public CVServiceImpl(
            CVRepository cvRepository,
            AlumnoRepository alumnoRepository,
            ExperienciaRepository experienciaRepository,
            FormacionRepository formacionRepository,
            HabilidadTecnicaRepository habilidadRepository,
            IdiomaRepository idiomaRepository,
            CertificacionRepository certificacionRepository) {
        this.cvRepository = cvRepository;
        this.alumnoRepository = alumnoRepository;
        this.experienciaRepository = experienciaRepository;
        this.formacionRepository = formacionRepository;
        this.habilidadRepository = habilidadRepository;
        this.idiomaRepository = idiomaRepository;
        this.certificacionRepository = certificacionRepository;
    }

    @Override
    @Transactional
    public CvResponseDto crearParaAlumno(Long idAlumno, CvContenidoRequestDto contenido) {
        validarContenido(contenido);
        if (cvRepository.findByAlumnoIdAlumno(idAlumno).isPresent()) {
            throw new IllegalArgumentException("El alumno ya tiene un CV");
        }
        Alumno alumno = alumnoRepository.findById(idAlumno)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con ID: " + idAlumno));

        CV cv = new CV();
        cv.setAlumno(alumno);
        alumno.setCv(cv);
        reemplazarContenido(cv, contenido);
        return mapToResponseDto(cvRepository.save(cv));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CvResponseDto> buscarPorId(Long id) {
        return cvRepository.findById(id).map(this::mapToResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CvResponseDto> buscarPorAlumno(Long idAlumno) {
        return cvRepository.findByAlumnoIdAlumno(idAlumno).map(this::mapToResponseDto);
    }

    @Override
    @Transactional
    public CvResponseDto actualizarContenido(Long id, CvContenidoRequestDto contenido) {
        validarContenido(contenido);
        CV cv = cvRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CV no encontrado con ID: " + id));
        reemplazarContenido(cv, contenido);
        return mapToResponseDto(cvRepository.save(cv));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        CV cv = cvRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CV no encontrado con ID: " + id));
        cvRepository.delete(cv);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CvResponseDto> filtrar(CvFiltroRequestDto filtros) {
        if (filtros == null) {
            throw new IllegalArgumentException("Los filtros son obligatorios");
        }

        boolean filtrarCertificacion = tieneTexto(filtros.certificacion());
        boolean filtrarHabilidad = tieneTexto(filtros.habilidad());
        boolean filtrarIdioma = tieneTexto(filtros.idioma());
        boolean filtrarNivel = tieneTexto(filtros.nivelIdioma());
        if (filtrarIdioma != filtrarNivel) {
            throw new IllegalArgumentException("Para filtrar por idioma hay que indicar también el nivel");
        }

        List<CV> cvs = cvRepository.findAll();
        if (filtrarCertificacion) {
            intersectar(cvs, cvRepository.findByCertificacion(filtros.certificacion().trim()));
        }
        if (filtrarHabilidad) {
            intersectar(cvs, cvRepository.findByHabilidad(filtros.habilidad().trim()));
        }
        if (filtrarIdioma) {
            intersectar(cvs, cvRepository.findByIdiomaYNivel(
                    filtros.idioma().trim(),
                    filtros.nivelIdioma().trim()
            ));
        }
        return cvs.stream().map(this::mapToResponseDto).toList();
    }

    private void reemplazarContenido(CV cv, CvContenidoRequestDto contenido) {
        Long idAlumno = cv.getAlumno().getIdAlumno();
        List<CvBloqueRequestDto> ordenBloques = validarYObtenerOrden(contenido);
        Map<CvBloqueRequestDto, Integer> posiciones = new HashMap<>();
        IntStream.range(0, ordenBloques.size()).forEach(posicion ->
                posiciones.put(ordenBloques.get(posicion), posicion));

        List<Experiencia> experiencias = cargarParaAlumno(
                contenido.idsExperiencias(),
                experienciaRepository::findById,
                experiencia -> idDelAlumno(experiencia.getAlumno()),
                idAlumno,
                "Experiencia"
        );
        List<Formacion> formaciones = cargarParaAlumno(
                contenido.idsFormaciones(),
                formacionRepository::findById,
                formacion -> idDelAlumno(formacion.getAlumno()),
                idAlumno,
                "Formación"
        );
        List<HabilidadTecnica> habilidades = cargarParaAlumno(
                contenido.idsHabilidades(),
                habilidadRepository::findById,
                habilidad -> idDelAlumno(habilidad.getAlumno()),
                idAlumno,
                "Habilidad"
        );
        List<Idioma> idiomas = cargarParaAlumno(
                contenido.idsIdiomas(),
                idiomaRepository::findById,
                idioma -> idDelAlumno(idioma.getAlumno()),
                idAlumno,
                "Idioma"
        );
        List<Certificacion> certificaciones = cargarParaAlumno(
                contenido.idsCertificaciones(),
                certificacionRepository::findById,
                certificacion -> idDelAlumno(certificacion.getAlumno()),
                idAlumno,
                "Certificación"
        );

        cv.getExperiencias().clear();
        experiencias.forEach(experiencia -> cv.getExperiencias()
                .add(new CvExperiencia(
                        null,
                        cv,
                        experiencia,
                        obtenerPosicion(posiciones, TipoBloqueCv.EXPERIENCIA, experiencia.getIdExperiencia())
                )));

        cv.getFormaciones().clear();
        formaciones.forEach(formacion -> cv.getFormaciones()
                .add(new CvFormacion(
                        null,
                        cv,
                        formacion,
                        obtenerPosicion(posiciones, TipoBloqueCv.FORMACION, formacion.getIdFormacion())
                )));

        cv.getHabilidades().clear();
        habilidades.forEach(habilidad -> cv.getHabilidades()
                .add(new CvHabilidad(
                        null,
                        cv,
                        habilidad,
                        obtenerPosicion(posiciones, TipoBloqueCv.HABILIDAD, habilidad.getIdHabilidadTecnica())
                )));

        cv.getIdiomas().clear();
        idiomas.forEach(idioma -> cv.getIdiomas()
                .add(new CvIdioma(
                        null,
                        cv,
                        idioma,
                        obtenerPosicion(posiciones, TipoBloqueCv.IDIOMA, idioma.getIdIdioma())
                )));

        cv.getCertificaciones().clear();
        certificaciones.forEach(certificacion -> cv.getCertificaciones()
                .add(new CvCertificacion(
                        null,
                        cv,
                        certificacion,
                        obtenerPosicion(posiciones, TipoBloqueCv.CERTIFICACION, certificacion.getIdCertificacion())
                )));
    }

    private List<CvBloqueRequestDto> validarYObtenerOrden(CvContenidoRequestDto contenido) {
        List<CvBloqueRequestDto> seleccionados = new ArrayList<>();
        contenido.idsExperiencias().forEach(id -> seleccionados.add(
                new CvBloqueRequestDto(TipoBloqueCv.EXPERIENCIA, id)));
        contenido.idsFormaciones().forEach(id -> seleccionados.add(
                new CvBloqueRequestDto(TipoBloqueCv.FORMACION, id)));
        contenido.idsHabilidades().forEach(id -> seleccionados.add(
                new CvBloqueRequestDto(TipoBloqueCv.HABILIDAD, id)));
        contenido.idsIdiomas().forEach(id -> seleccionados.add(
                new CvBloqueRequestDto(TipoBloqueCv.IDIOMA, id)));
        contenido.idsCertificaciones().forEach(id -> seleccionados.add(
                new CvBloqueRequestDto(TipoBloqueCv.CERTIFICACION, id)));

        List<CvBloqueRequestDto> orden = contenido.ordenBloques() == null
                ? seleccionados
                : contenido.ordenBloques();
        if (orden.stream().anyMatch(bloque -> bloque == null
                || bloque.tipo() == null
                || bloque.idElemento() == null)) {
            throw new IllegalArgumentException("El orden contiene bloques incompletos");
        }

        Set<CvBloqueRequestDto> seleccionUnica = new HashSet<>(seleccionados);
        Set<CvBloqueRequestDto> ordenUnico = new HashSet<>(orden);
        if (ordenUnico.size() != orden.size() || !ordenUnico.equals(seleccionUnica)) {
            throw new IllegalArgumentException(
                    "ordenBloques debe contener una sola vez cada elemento seleccionado en las listas de IDs");
        }
        return orden;
    }

    private int obtenerPosicion(
            Map<CvBloqueRequestDto, Integer> posiciones,
            TipoBloqueCv tipo,
            Long idElemento) {
        return posiciones.get(new CvBloqueRequestDto(tipo, idElemento));
    }

    private <T> List<T> cargarParaAlumno(
            List<Long> ids,
            Function<Long, Optional<T>> buscarPorId,
            Function<T, Long> obtenerIdAlumno,
            Long idAlumno,
            String tipo) {
        return ids.stream()
                .map(id -> {
                    T elemento = buscarPorId.apply(id)
                            .orElseThrow(() -> new EntityNotFoundException(
                                    tipo + " no encontrada con ID: " + id));
                    if (!Objects.equals(idAlumno, obtenerIdAlumno.apply(elemento))) {
                        throw new IllegalArgumentException(
                                tipo + " con ID " + id + " no pertenece al alumno del CV");
                    }
                    return elemento;
                })
                .toList();
    }

    private void validarContenido(CvContenidoRequestDto contenido) {
        if (contenido == null) {
            throw new IllegalArgumentException("El contenido del CV es obligatorio");
        }
        validarIds(contenido.idsExperiencias(), "experiencias");
        validarIds(contenido.idsFormaciones(), "formaciones");
        validarIds(contenido.idsHabilidades(), "habilidades");
        validarIds(contenido.idsIdiomas(), "idiomas");
        validarIds(contenido.idsCertificaciones(), "certificaciones");
    }

    private void validarIds(List<Long> ids, String nombreCampo) {
        if (ids == null || ids.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("La lista de " + nombreCampo + " es obligatoria y no puede contener null");
        }
        if (new HashSet<>(ids).size() != ids.size()) {
            throw new IllegalArgumentException("La lista de " + nombreCampo + " contiene IDs duplicados");
        }
    }

    private Long idDelAlumno(Alumno alumno) {
        return alumno == null ? null : alumno.getIdAlumno();
    }

    private boolean tieneTexto(String texto) {
        return texto != null && !texto.isBlank();
    }

    private void intersectar(List<CV> cvs, List<CV> coincidentes) {
        Set<Long> idsCoincidentes = coincidentes.stream()
                .map(CV::getIdCV)
                .collect(java.util.stream.Collectors.toSet());
        cvs.removeIf(cv -> !idsCoincidentes.contains(cv.getIdCV()));
    }

    private CvResponseDto mapToResponseDto(CV cv) {
        Alumno alumno = cv.getAlumno();
        AlumnoResponseDto alumnoDto = new AlumnoResponseDto(
                alumno.getIdAlumno(),
                alumno.getNombre(),
                alumno.getApellidos(),
                alumno.getDni(),
                alumno.getEmail(),
                alumno.getNumeroSeguridadSocial(),
                alumno.isTutorialCompletado(),
                alumno.getFechaNacimiento(),
                cv.getIdCV()
        );

        List<CvBloqueResponseDto> ordenBloques = new ArrayList<>();
        cv.getExperiencias().forEach(enlace -> ordenBloques.add(new CvBloqueResponseDto(
                TipoBloqueCv.EXPERIENCIA,
                enlace.getExperiencia().getIdExperiencia(),
                enlace.getPosicion()
        )));
        cv.getFormaciones().forEach(enlace -> ordenBloques.add(new CvBloqueResponseDto(
                TipoBloqueCv.FORMACION,
                enlace.getFormacion().getIdFormacion(),
                enlace.getPosicion()
        )));
        cv.getHabilidades().forEach(enlace -> ordenBloques.add(new CvBloqueResponseDto(
                TipoBloqueCv.HABILIDAD,
                enlace.getHabilidad().getIdHabilidadTecnica(),
                enlace.getPosicion()
        )));
        cv.getIdiomas().forEach(enlace -> ordenBloques.add(new CvBloqueResponseDto(
                TipoBloqueCv.IDIOMA,
                enlace.getIdioma().getIdIdioma(),
                enlace.getPosicion()
        )));
        cv.getCertificaciones().forEach(enlace -> ordenBloques.add(new CvBloqueResponseDto(
                TipoBloqueCv.CERTIFICACION,
                enlace.getCertificacion().getIdCertificacion(),
                enlace.getPosicion()
        )));
        ordenBloques.sort(Comparator.comparingInt(CvBloqueResponseDto::posicion));

        return new CvResponseDto(
                cv.getIdCV(),
                alumnoDto,
                cv.getExperiencias().stream()
                        .sorted(Comparator.comparingInt(CvExperiencia::getPosicion))
                        .map(this::mapToResponseDto).toList(),
                cv.getFormaciones().stream()
                        .sorted(Comparator.comparingInt(CvFormacion::getPosicion))
                        .map(this::mapToResponseDto).toList(),
                cv.getHabilidades().stream()
                        .sorted(Comparator.comparingInt(CvHabilidad::getPosicion))
                        .map(this::mapToResponseDto).toList(),
                cv.getIdiomas().stream()
                        .sorted(Comparator.comparingInt(CvIdioma::getPosicion))
                        .map(this::mapToResponseDto).toList(),
                cv.getCertificaciones().stream()
                        .sorted(Comparator.comparingInt(CvCertificacion::getPosicion))
                        .map(this::mapToResponseDto).toList(),
                ordenBloques
        );
    }

    private ExperienciaResponseDto mapToResponseDto(CvExperiencia enlace) {
        Experiencia experiencia = enlace.getExperiencia();
        return new ExperienciaResponseDto(
                experiencia.getIdExperiencia(),
                experiencia.getPuesto(),
                experiencia.getEmpresa(),
                experiencia.getPeriodo(),
                experiencia.getDescripcion()
        );
    }

    private FormacionResponseDto mapToResponseDto(CvFormacion enlace) {
        Formacion formacion = enlace.getFormacion();
        return new FormacionResponseDto(
                formacion.getIdFormacion(),
                formacion.getTitulo(),
                formacion.getCentro(),
                formacion.getPeriodo(),
                formacion.isEnCurso()
        );
    }

    private HabilidadTecnicaResponseDto mapToResponseDto(CvHabilidad enlace) {
        HabilidadTecnica habilidad = enlace.getHabilidad();
        return new HabilidadTecnicaResponseDto(
                habilidad.getIdHabilidadTecnica(),
                habilidad.getNombre(),
                habilidad.getDescripcion(),
                habilidad.isDestacada()
        );
    }

    private IdiomaResponseDto mapToResponseDto(CvIdioma enlace) {
        Idioma idioma = enlace.getIdioma();
        return new IdiomaResponseDto(idioma.getIdIdioma(), idioma.getNombre(), idioma.getNivel());
    }

    private CertificacionResponseDto mapToResponseDto(CvCertificacion enlace) {
        Certificacion certificacion = enlace.getCertificacion();
        return new CertificacionResponseDto(
                certificacion.getIdCertificacion(),
                certificacion.getTitulo(),
                certificacion.getEmision(),
                certificacion.getFechaObtencion()
        );
    }
}
