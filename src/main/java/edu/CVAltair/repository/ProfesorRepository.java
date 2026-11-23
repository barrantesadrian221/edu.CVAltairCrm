package edu.CVAltair.repository;

import edu.CVAltair.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    Optional<Profesor> findByDni(String dni);

    Optional<Profesor> findByGmailIgnoreCase(String gmail);

    boolean existsByDni(String dni);

    boolean existsByGmailIgnoreCase(String gmail);

    List<Profesor> findByDepartamentoIgnoreCase(String departamento);

    List<Profesor> findByApellidosContainingIgnoreCase(String apellidos);
}
