package edu.CVAltair.repository;

import edu.CVAltair.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Define el contrato de operaciones relacionado con alumno.

 * @author ABR
 */
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    /**
     * Busca alumno por DNI.
     * @param dni
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<Alumno> findByDni(String dni);
    /**
     * Busca alumno por correo electrónico.
     * @param email
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<Alumno> findByEmail(String email);
    /**
     * Busca alumno por número seguridad social.
     * @param numeroSeguridadSocial
     * @return el resultado, si existe un registro que coincide con los criterios indicados.
     */
    Optional<Alumno> findByNumeroSeguridadSocial(String numeroSeguridadSocial);
    /**
     * Comprueba si ya existe alumno con el criterio indicado.
     * @param email
     * @return true si se cumple la condición comprobada; false en caso contrario.
     */
    boolean existsByEmail(String email);
    /**
     * Comprueba si ya existe alumno con el criterio indicado.
     * @param dni
     * @return true si se cumple la condición comprobada; false en caso contrario.
     */
    boolean existsByDni(String dni);
    /**
     * Comprueba si ya existe alumno con el criterio indicado.
     * @param numeroSeguridadSocial
     * @return true si se cumple la condición comprobada; false en caso contrario.
     */
    boolean existsByNumeroSeguridadSocial(String numeroSeguridadSocial);

}
