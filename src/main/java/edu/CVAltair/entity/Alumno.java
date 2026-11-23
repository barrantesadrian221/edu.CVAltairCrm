package edu.CVAltair.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alumnos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_alumno_dni", columnNames = "dni"),
        @UniqueConstraint(name = "uk_alumno_email", columnNames = "email"),
        @UniqueConstraint(name = "uk_alumno_n_seguridad_social", columnNames = "n_seguridad_social")
})
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private Long idAlumno;

    @Column(name ="nombre", nullable = false, length = 40)
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 150)
    private String apellidos;

    @Column(name = "dni", nullable = false, length = 10)
    private String dni;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "n_seguridad_social", nullable = false, length = 12)
    private String numeroSeguridadSocial;

    @Column(name = "tutorial_completado", nullable = false)
    private Boolean tutorialCompletado;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @OneToOne(mappedBy = "alumno", fetch = FetchType.LAZY)
    private CV cv;

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<Experiencia> experiencias = new ArrayList<>();

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<Formacion> formaciones = new ArrayList<>();

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<HabilidadTecnica> habilidades = new ArrayList<>();

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<Idioma> idiomas = new ArrayList<>();

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<Certificacion> certificaciones = new ArrayList<>();

    public Alumno() {
    }

    public Alumno(Long idAlumno,String nombre, String apellidos, String dni, String email, String numeroSeguridadSocial, Boolean tutorialCompletado, LocalDate fechaNacimiento) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.numeroSeguridadSocial = numeroSeguridadSocial;
        this.tutorialCompletado = tutorialCompletado;
        this.fechaNacimiento = fechaNacimiento;
    }


    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroSeguridadSocial() {
        return numeroSeguridadSocial;
    }

    public void setNumeroSeguridadSocial(String numeroSeguridadSocial) {
        this.numeroSeguridadSocial = numeroSeguridadSocial;
    }

    public boolean isTutorialCompletado() {
        return tutorialCompletado;
    }

    public void setTutorialCompletado(Boolean tutorialCompletado) {
        this.tutorialCompletado = tutorialCompletado;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }

    public List<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    public List<Formacion> getFormaciones() {
        return formaciones;
    }

    public void setFormaciones(List<Formacion> formaciones) {
        this.formaciones = formaciones;
    }

    public List<HabilidadTecnica> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<HabilidadTecnica> habilidades) {
        this.habilidades = habilidades;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public List<Certificacion> getCertificaciones() {
        return certificaciones;
    }

    public void setCertificaciones(List<Certificacion> certificaciones) {
        this.certificaciones = certificaciones;
    }
}
