package edu.CVAltair.repository;

import edu.CVAltair.entity.Certificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificacionRepository extends JpaRepository<Certificacion, Long> {

    Optional<Certificacion> findByTituloIgnoreCase(String titulo);

    List<Certificacion> findByTituloContainingIgnoreCase(String titulo);

    List<Certificacion> findByEmisionContainingIgnoreCase(String emision);

    List<Certificacion> findByAlumnoIdAlumno(Long idAlumno);
}
