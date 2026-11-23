package edu.CVAltair.repository;

import edu.CVAltair.entity.Formacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormacionRepository extends JpaRepository<Formacion, Long> {

    List<Formacion> findByTituloContainingIgnoreCase(String titulo);

    List<Formacion> findByCentroContainingIgnoreCase(String centro);

    List<Formacion> findByEnCurso(boolean enCurso);

    List<Formacion> findByAlumnoIdAlumno(Long idAlumno);
}
