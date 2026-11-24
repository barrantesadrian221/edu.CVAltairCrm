package edu.CVAltair.repository;

import edu.CVAltair.entity.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Define el contrato de operaciones relacionado con idioma.

 * @author ABR
 */
@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Long> {

    /**
     * Busca idioma por nombre ignore case and nivel ignore case.
     * @param nombre
     * @param nivel
     * @return lista con los registros obtenidos.
     */
    List<Idioma> findByNombreIgnoreCaseAndNivelIgnoreCase(String nombre, String nivel);

    /**
     * Busca idioma por nombre containing ignore case.
     * @param nombre
     * @return lista con los registros obtenidos.
     */
    List<Idioma> findByNombreContainingIgnoreCase(String nombre);

    /**
     * Busca idioma por alumno identificador alumno.
     * @param idAlumno
     * @return lista con los registros obtenidos.
     */
    List<Idioma> findByAlumnoIdAlumno(Long idAlumno);
}
