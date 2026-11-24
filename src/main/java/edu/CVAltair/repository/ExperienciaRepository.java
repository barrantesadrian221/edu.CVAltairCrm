package edu.CVAltair.repository;

import edu.CVAltair.entity.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Define el contrato de operaciones relacionado con experiencia.

 * @author ABR
 */
@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {

    /**
     * Busca experiencia por empresa containing ignore case.
     * @param empresa
     * @return lista con los registros obtenidos.
     */
    List<Experiencia> findByEmpresaContainingIgnoreCase(String empresa);

    /**
     * Busca experiencia por puesto containing ignore case.
     * @param puesto
     * @return lista con los registros obtenidos.
     */
    List<Experiencia> findByPuestoContainingIgnoreCase(String puesto);

    /**
     * Busca experiencia por alumno identificador alumno.
     * @param idAlumno
     * @return lista con los registros obtenidos.
     */
    List<Experiencia> findByAlumnoIdAlumno(Long idAlumno);
}
