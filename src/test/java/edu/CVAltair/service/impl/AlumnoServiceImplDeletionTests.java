package edu.CVAltair.service.impl;

import edu.CVAltair.entity.Alumno;
import edu.CVAltair.entity.CV;
import edu.CVAltair.repository.AlumnoRepository;
import edu.CVAltair.repository.CVRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AlumnoServiceImplDeletionTests {

    private AlumnoRepository alumnoRepository;
    private CVRepository cvRepository;
    private AlumnoServiceImpl alumnoService;

    @BeforeEach
    void setUp() {
        alumnoRepository = mock(AlumnoRepository.class);
        cvRepository = mock(CVRepository.class);
        alumnoService = new AlumnoServiceImpl(alumnoRepository, cvRepository);
    }

    @Test
    void eliminaCvYVinculosAntesDeEliminarAlumno() {
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(4L);
        CV cv = new CV();
        when(alumnoRepository.findById(4L)).thenReturn(Optional.of(alumno));
        when(cvRepository.findByAlumnoIdAlumno(4L)).thenReturn(Optional.of(cv));

        alumnoService.eliminarPorId(4L);

        InOrder order = inOrder(alumnoRepository, cvRepository);
        order.verify(alumnoRepository).findById(4L);
        order.verify(cvRepository).findByAlumnoIdAlumno(4L);
        order.verify(cvRepository).delete(cv);
        order.verify(cvRepository).flush();
        order.verify(alumnoRepository).delete(alumno);
    }

    @Test
    void alumnoInexistenteNoSeElimina() {
        when(alumnoRepository.findById(4L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> alumnoService.eliminarPorId(4L));
    }
}
