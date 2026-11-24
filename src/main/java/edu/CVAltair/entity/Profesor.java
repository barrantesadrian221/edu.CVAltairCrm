package edu.CVAltair.entity;

import jakarta.persistence.*;

import java.util.Objects;

/** Representa profesor en la aplicación.
 *
 * @author ABR
 */
@Entity
@Table(name = "profesores", uniqueConstraints = {
        @UniqueConstraint(name = "uk_dni_profesor", columnNames = "dni"),
        @UniqueConstraint(name = "uk_gmail_profesor", columnNames = "gmail")
})

public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_profesor")
    private Long idProfesor;

    @Column( name = "dni", nullable = false, length = 10)
    private String dni;

    @Column( name ="gmail", nullable = false , length = 40)
    private String gmail;

    @Column ( name ="nombre", nullable = false, length = 50)
    private String nombre;

    @Column ( name ="apellidos", nullable = false, length = 150)
    private String apellidos;

    @Column ( name ="departamento", nullable = false)
    private String departamento;

    /**
     * Crea una instancia de profesor.
     */
    public Profesor() {
    }

    /**
     * Crea una instancia de profesor.
     * @param idProfesor
     * @param dni
     * @param gmail
     * @param nombre
     * @param apellidos
     * @param departamento
     */
    public Profesor(Long idProfesor, String dni, String gmail, String nombre, String apellidos, String departamento) {
        this.idProfesor = idProfesor;
        this.dni = dni;
        this.gmail = gmail;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.departamento = departamento;
    }

    /**
     * Obtiene identificador profesor de profesor.
     * @return resultado obtenido al ejecutar la operación.
     */
    public Long getIdProfesor() {
        return idProfesor;
    }

    /**
     * Establece identificador profesor en profesor.
     * @param idProfesor
     */
    public void setIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }

    /**
     * Obtiene DNI de profesor.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece DNI en profesor.
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene gmail de profesor.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getGmail() {
        return gmail;
    }

    /**
     * Establece gmail en profesor.
     * @param gmail
     */
    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    /**
     * Obtiene nombre de profesor.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece nombre en profesor.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene apellidos de profesor.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece apellidos en profesor.
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene departamento de profesor.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Establece departamento en profesor.
     * @param departamento
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
