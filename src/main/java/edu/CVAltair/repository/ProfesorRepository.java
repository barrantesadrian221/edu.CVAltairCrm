package edu.CVAltair.repository;

import edu.CVAltair.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Define el contrato de operaciones relacionado con profesor.

 * @author ABR
 */
@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    /**
     * Busca profesor por DNI.
     * @param dni
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<Profesor> findByDni(String dni);

    /**
     * Busca profesor por gmail ignore case.
     * @param gmail
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<Profesor> findByGmailIgnoreCase(String gmail);

    /**
     * Comprueba si ya existe profesor con el criterio indicado.
     * @param dni
     * @return true si se cumple la condición comprobada; false en caso contrario.
     */
    boolean existsByDni(String dni);

    /**
     * Comprueba si ya existe profesor con el criterio indicado.
     * @param gmail
     * @return true si se cumple la condición comprobada; false en caso contrario.
     */
    boolean existsByGmailIgnoreCase(String gmail);

    /**
     * Busca profesor por departamento ignore case.
     * @param departamento
     * @return lista con los registros obtenidos.
     */
    List<Profesor> findByDepartamentoIgnoreCase(String departamento);

    /**
     * Busca profesor por apellidos containing ignore case.
     * @param apellidos
     * @return lista con los registros obtenidos.
     */
    List<Profesor> findByApellidosContainingIgnoreCase(String apellidos);
}
