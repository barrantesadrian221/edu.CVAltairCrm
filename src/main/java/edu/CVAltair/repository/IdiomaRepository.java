package edu.CVAltair.repository;

import edu.CVAltair.entity.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Long> {

    List<Idioma> findByNombreIgnoreCaseAndNivelIgnoreCase(String nombre, String nivel);

    List<Idioma> findByNombreContainingIgnoreCase(String nombre);

    List<Idioma> findByAlumnoIdAlumno(Long idAlumno);
}
