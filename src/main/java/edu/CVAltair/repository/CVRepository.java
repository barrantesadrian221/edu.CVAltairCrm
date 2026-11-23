package edu.CVAltair.repository;

import edu.CVAltair.entity.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CVRepository extends JpaRepository<CV, Long> {

    Optional<CV> findByAlumnoIdAlumno(Long idAlumno);

    @Query("""
            select distinct cv
            from CV cv
            join cv.certificaciones cvCertificacion
            join cvCertificacion.certificacion certificacion
            where lower(certificacion.titulo) = lower(:titulo)
            """)
    List<CV> findByCertificacion(@Param("titulo") String titulo);

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

    @Query("""
            select distinct cv
            from CV cv
            join cv.habilidades cvHabilidad
            join cvHabilidad.habilidad habilidad
            where lower(habilidad.nombre) = lower(:nombre)
            """)
    List<CV> findByHabilidad(@Param("nombre") String nombre);
}
