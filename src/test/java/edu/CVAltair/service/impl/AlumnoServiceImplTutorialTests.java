package edu.CVAltair.service.impl;

import edu.CVAltair.dto.EstadoTutorial;
import edu.CVAltair.dto.TutorialResponseDto;
import edu.CVAltair.entity.Alumno;
import edu.CVAltair.repository.AlumnoRepository;
import edu.CVAltair.repository.CVRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/** Pruebas del flujo del tutorial, creadas con ayuda de IA. */
class AlumnoServiceImplTutorialTests {

    private AlumnoRepository alumnoRepository;
    private CVRepository cvRepository;
    private AlumnoServiceImpl alumnoService;
    private Alumno alumno;

    /**
     * Prepara las dependencias y los datos necesarios para la prueba.
     */
    @BeforeEach
    void setUp() {
        alumnoRepository = mock(AlumnoRepository.class);
        cvRepository = mock(CVRepository.class);
        alumnoService = new AlumnoServiceImpl(alumnoRepository, cvRepository);
        alumno = new Alumno();
        alumno.setIdAlumno(7L);
        alumno.setTutorialCompletado(false);
        when(alumnoRepository.findById(7L)).thenReturn(Optional.of(alumno));
        when(alumnoRepository.save(any(Alumno.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    /**
     * Verifica que primer acceso muestra el tutorial pendiente con sus pasos.
     */
    @Test
    void primerAccesoMuestraElTutorialPendienteConSusPasos() {
        TutorialResponseDto respuesta = alumnoService.consultarTutorial(7L);

        assertEquals(EstadoTutorial.PENDIENTE, respuesta.estado());
        assertTrue(respuesta.mostrarTutorial());
        assertEquals(6, respuesta.pasos().size());
    }

    /**
     * Verifica que omitir y completar actualizan el estado del tutorial.
     */
    @Test
    void omitirYCompletarActualizanElEstadoDelTutorial() {
        TutorialResponseDto omitido = alumnoService.omitirTutorial(7L);

        assertEquals(EstadoTutorial.OMITIDO, omitido.estado());
        assertFalse(omitido.mostrarTutorial());

        TutorialResponseDto completado = alumnoService.completarTutorial(7L);

        assertEquals(EstadoTutorial.COMPLETADO, completado.estado());
        assertFalse(completado.mostrarTutorial());
        assertTrue(alumno.isTutorialCompletado());
        assertFalse(alumno.isTutorialOmitido());
    }

    /**
     * Verifica que tutorial de alumno inexistente devuelve no encontrado.
     */
    @Test
    void tutorialDeAlumnoInexistenteDevuelveNoEncontrado() {
        when(alumnoRepository.findById(8L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> alumnoService.consultarTutorial(8L));
    }
}
