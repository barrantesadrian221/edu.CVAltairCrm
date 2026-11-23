package edu.CVAltair.entity;

import jakarta.persistence.*;

import java.util.Objects;

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

    public Profesor() {
    }

    public Profesor(Long idProfesor, String dni, String gmail, String nombre, String apellidos, String departamento) {
        this.idProfesor = idProfesor;
        this.dni = dni;
        this.gmail = gmail;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.departamento = departamento;
    }

    public Long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
