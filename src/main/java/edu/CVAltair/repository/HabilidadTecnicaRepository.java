package edu.CVAltair.repository;

import edu.CVAltair.entity.HabilidadTecnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Define el contrato de operaciones relacionado con habilidad técnica.

 * @author ABR
 */
@Repository
public interface HabilidadTecnicaRepository extends JpaRepository<HabilidadTecnica, Long> {

    /**
     * Busca habilidad técnica por nombre ignore case.
     * @param nombre
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<HabilidadTecnica> findByNombreIgnoreCase(String nombre);

    /**
     * Busca habilidad técnica por nombre containing ignore case.
     * @param nombre
     * @return lista con los registros obtenidos.
     */
    List<HabilidadTecnica> findByNombreContainingIgnoreCase(String nombre);

    /**
     * Busca habilidad técnica por destacada.
     * @param destacada
     * @return lista con los registros obtenidos.
     */
    List<HabilidadTecnica> findByDestacada(boolean destacada);

    /**
     * Busca habilidad técnica por alumno identificador alumno.
     * @param idAlumno
     * @return lista con los registros obtenidos.
     */
    List<HabilidadTecnica> findByAlumnoIdAlumno(Long idAlumno);
}
