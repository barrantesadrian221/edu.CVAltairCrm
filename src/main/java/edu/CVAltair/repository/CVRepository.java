package edu.CVAltair.repository;

import edu.CVAltair.entity.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Define el contrato de operaciones relacionado con currículum.

 * @author ABR
 */
@Repository
public interface CVRepository extends JpaRepository<CV, Long> {


    /**
     * Busca currículum por alumno identificador alumno.
     * @param idAlumno
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<CV> findByAlumnoIdAlumno(Long idAlumno);


    /**
     * Busca currículum por certificación.
     * @param titulo
     * @return lista con los registros obtenidos.
     */
    @Query("""
            select distinct cv
            from CV cv
            join cv.certificaciones cvCertificacion
            join cvCertificacion.certificacion certificacion
            where lower(certificacion.titulo) = lower(:titulo)
            """)
    List<CV> findByCertificacion(@Param("titulo") String titulo);


    /**
     * Busca currículum por idioma y nivel.
     * @param nombre
     * @param nivel
     * @return lista con los registros obtenidos.
     */
    @Query("""
            select distinct cv
            from CV cv
            join cv.idiomas cvIdioma
            join cvIdioma.idioma idioma
            where lower(idioma.nombre) = lower(:nombre)
              and lower(idioma.nivel) = lower(:nivel)
            """)
    List<CV> findByIdiomaYNivel(
            @Param("nombre") String nombre,
            @Param("nivel") String nivel
    );


    /**
     * Busca currículum por habilidad.
     * @param nombre
     * @return lista con los registros obtenidos.
     */
    @Query("""
            select distinct cv
            from CV cv
            join cv.habilidades cvHabilidad
            join cvHabilidad.habilidad habilidad
            where lower(habilidad.nombre) = lower(:nombre)
            """)
    List<CV> findByHabilidad(@Param("nombre") String nombre);
}
