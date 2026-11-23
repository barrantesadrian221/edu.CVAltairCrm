package edu.CVAltair.repository;

import edu.CVAltair.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    Optional<Alumno> findByDni(String dni);
    Optional<Alumno> findByEmail(String email);
    Optional<Alumno> findByNumeroSeguridadSocial(String numeroSeguridadSocial);
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
    boolean existsByNumeroSeguridadSocial(String numeroSeguridadSocial);

    // Otras consultas derivadas que podrías añadir:
    // List<Alumno> findByApellidosContainingIgnoreCase(String apellidos);
    // List<Alumno> findByNombreContainingIgnoreCase(String nombre);
    // List<Alumno> findByTutorialCompletado(boolean completado);
    // long countByTutorialCompletado(boolean completado);
}
