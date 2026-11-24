package edu.CVAltair.repository;

import edu.CVAltair.entity.Formacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Define el contrato de operaciones relacionado con formación.

 * @author ABR
 */
@Repository
public interface FormacionRepository extends JpaRepository<Formacion, Long> {

    /**
     * Busca formación por titulo containing ignore case.
     * @param titulo
     * @return lista con los registros obtenidos.
     */
    List<Formacion> findByTituloContainingIgnoreCase(String titulo);

    /**
     * Busca formación por centro containing ignore case.
     * @param centro
     * @return lista con los registros obtenidos.
     */
    List<Formacion> findByCentroContainingIgnoreCase(String centro);

    /**
     * Busca formación por en curso.
     * @param enCurso
     * @return lista con los registros obtenidos.
     */
    List<Formacion> findByEnCurso(boolean enCurso);

    /**
     * Busca formación por alumno identificador alumno.
     * @param idAlumno
     * @return lista con los registros obtenidos.
     */
    List<Formacion> findByAlumnoIdAlumno(Long idAlumno);
}
