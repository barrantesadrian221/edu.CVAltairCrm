package edu.CVAltair.repository;

import edu.CVAltair.entity.Certificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Define el contrato de operaciones relacionado con certificación.

 * @author ABR
 */
@Repository
public interface CertificacionRepository extends JpaRepository<Certificacion, Long> {

    /**
     * Busca certificación por titulo ignore case.
     * @param titulo
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<Certificacion> findByTituloIgnoreCase(String titulo);

    /**
     * Busca certificación por titulo containing ignore case.
     * @param titulo
     * @return lista con los registros obtenidos.
     */
    List<Certificacion> findByTituloContainingIgnoreCase(String titulo);

    /**
     * Busca certificación por emision containing ignore case.
     * @param emision
     * @return lista con los registros obtenidos.
     */
    List<Certificacion> findByEmisionContainingIgnoreCase(String emision);

    /**
     * Busca certificación por alumno identificador alumno.
     * @param idAlumno
     * @return lista con los registros obtenidos.
     */
    List<Certificacion> findByAlumnoIdAlumno(Long idAlumno);
}
