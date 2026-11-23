package edu.CVAltair.repository;

import edu.CVAltair.entity.HabilidadTecnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabilidadTecnicaRepository extends JpaRepository<HabilidadTecnica, Long> {

    Optional<HabilidadTecnica> findByNombreIgnoreCase(String nombre);

    List<HabilidadTecnica> findByNombreContainingIgnoreCase(String nombre);

    List<HabilidadTecnica> findByDestacada(boolean destacada);

    List<HabilidadTecnica> findByAlumnoIdAlumno(Long idAlumno);
}
