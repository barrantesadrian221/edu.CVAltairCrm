package edu.CVAltair.repository;

import edu.CVAltair.entity.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {

    List<Experiencia> findByEmpresaContainingIgnoreCase(String empresa);

    List<Experiencia> findByPuestoContainingIgnoreCase(String puesto);

    List<Experiencia> findByAlumnoIdAlumno(Long idAlumno);
}
