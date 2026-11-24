package edu.CVAltair.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Representa alumno en la aplicación.
 *
 * @author ABR
 */
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

    /** Indica si el alumno decidió omitir el tutorial. Implementado con ayuda de IA. */
    @Column(name = "tutorial_omitido", nullable = false, columnDefinition = "boolean default false")
    private boolean tutorialOmitido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @OneToOne(mappedBy = "alumno", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private CV cv;

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Experiencia> experiencias = new ArrayList<>();

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Formacion> formaciones = new ArrayList<>();

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<HabilidadTecnica> habilidades = new ArrayList<>();

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Idioma> idiomas = new ArrayList<>();

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Certificacion> certificaciones = new ArrayList<>();

    /**
     * Crea una instancia de alumno.
     */
    public Alumno() {
    }

    /**
     * Crea una instancia de alumno.
     * @param idAlumno
     * @param nombre
     * @param apellidos
     * @param dni
     * @param email
     * @param numeroSeguridadSocial
     * @param tutorialCompletado
     * @param fechaNacimiento
     */
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


    /**
     * Obtiene identificador alumno de alumno.
     * @return resultado obtenido al ejecutar la operación.
     */
    public Long getIdAlumno() {
        return idAlumno;
    }

    /**
     * Establece identificador alumno en alumno.
     * @param idAlumno
     */
    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    /**
     * Obtiene nombre de alumno.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece nombre en alumno.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene apellidos de alumno.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece apellidos en alumno.
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene DNI de alumno.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece DNI en alumno.
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene correo electrónico de alumno.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece correo electrónico en alumno.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene número seguridad social de alumno.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getNumeroSeguridadSocial() {
        return numeroSeguridadSocial;
    }

    /**
     * Establece número seguridad social en alumno.
     * @param numeroSeguridadSocial
     */
    public void setNumeroSeguridadSocial(String numeroSeguridadSocial) {
        this.numeroSeguridadSocial = numeroSeguridadSocial;
    }

    /**
     * Obtiene tutorial completado de alumno.
     * @return true si se cumple la condición comprobada; false en caso contrario.
     */
    public boolean isTutorialCompletado() {
        return tutorialCompletado;
    }

    /**
     * Establece tutorial completado en alumno.
     * @param tutorialCompletado
     */
    public void setTutorialCompletado(Boolean tutorialCompletado) {
        this.tutorialCompletado = tutorialCompletado;
    }

    /** Devuelve si el alumno omitió el tutorial. Implementado con ayuda de IA. */
    public boolean isTutorialOmitido() {
        return tutorialOmitido;
    }

    /** Guarda si el alumno omitió el tutorial. Implementado con ayuda de IA. */
    public void setTutorialOmitido(boolean tutorialOmitido) {
        this.tutorialOmitido = tutorialOmitido;
    }

    /**
     * Obtiene fecha nacimiento de alumno.
     * @return resultado obtenido al ejecutar la operación.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece fecha nacimiento en alumno.
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene CV de alumno.
     * @return resultado obtenido al ejecutar la operación.
     */
    public CV getCv() {
        return cv;
    }

    /**
     * Establece CV en alumno.
     * @param cv
     */
    public void setCv(CV cv) {
        this.cv = cv;
    }

    /**
     * Obtiene experiencias de alumno.
     * @return lista con los registros obtenidos.
     */
    public List<Experiencia> getExperiencias() {
        return experiencias;
    }

    /**
     * Establece experiencias en alumno.
     * @param experiencias
     */
    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    /**
     * Obtiene formaciones de alumno.
     * @return lista con los registros obtenidos.
     */
    public List<Formacion> getFormaciones() {
        return formaciones;
    }

    /**
     * Establece formaciones en alumno.
     * @param formaciones
     */
    public void setFormaciones(List<Formacion> formaciones) {
        this.formaciones = formaciones;
    }

    /**
     * Obtiene habilidades de alumno.
     * @return lista con los registros obtenidos.
     */
    public List<HabilidadTecnica> getHabilidades() {
        return habilidades;
    }

    /**
     * Establece habilidades en alumno.
     * @param habilidades
     */
    public void setHabilidades(List<HabilidadTecnica> habilidades) {
        this.habilidades = habilidades;
    }

    /**
     * Obtiene idiomas de alumno.
     * @return lista con los registros obtenidos.
     */
    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    /**
     * Establece idiomas en alumno.
     * @param idiomas
     */
    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    /**
     * Obtiene certificaciones de alumno.
     * @return lista con los registros obtenidos.
     */
    public List<Certificacion> getCertificaciones() {
        return certificaciones;
    }

    /**
     * Establece certificaciones en alumno.
     * @param certificaciones
     */
    public void setCertificaciones(List<Certificacion> certificaciones) {
        this.certificaciones = certificaciones;
    }
}
