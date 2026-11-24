package edu.CVAltair.entity;

import jakarta.persistence.*;


/**
 * Representa formación en la aplicación.
 * @author ABR
 */
@Entity

@Table(name = "formaciones")
public class Formacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_formacion")
    private Long idFormacion;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "centro", nullable = false, length = 150)
    private String centro;

    @Column(name = "periodo", nullable = false, length = 50)
    private String periodo;

    @Column(name = "en_curso", nullable = false)
    private boolean enCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;


    /**
     * Crea una instancia de formación.
     */
    public Formacion() {
    }

    /**
     * Crea una instancia de formación.
     * @param idFormacion
     * @param titulo
     * @param centro
     * @param periodo
     * @param enCurso
     */
    public Formacion(Long idFormacion, String titulo, String centro, String periodo, boolean enCurso) {
        this.idFormacion = idFormacion;
        this.titulo = titulo;
        this.centro = centro;
        this.periodo = periodo;
        this.enCurso = enCurso;
    }

    /**
     * Obtiene identificador formación de formación.
     * @return resultado obtenido al ejecutar la operación.
     */
    public Long getIdFormacion() {
        return idFormacion;
    }

    /**
     * Establece identificador formación en formación.
     * @param idFormacion
     */
    public void setIdFormacion(Long idFormacion) {
        this.idFormacion = idFormacion;
    }

    /**
     * Obtiene titulo de formación.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece titulo en formación.
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene centro de formación.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getCentro() {
        return centro;
    }

    /**
     * Establece centro en formación.
     * @param centro
     */
    public void setCentro(String centro) {
        this.centro = centro;
    }

    /**
     * Obtiene periodo de formación.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * Establece periodo en formación.
     * @param periodo
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    /**
     * Obtiene en curso de formación.
     * @return true si se cumple la condición comprobada; false en caso contrario.
     */
    public boolean isEnCurso() {
        return enCurso;
    }

    /**
     * Establece en curso en formación.
     * @param enCurso
     */
    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }

    /**
     * Obtiene alumno de formación.
     * @return resultado obtenido al ejecutar la operación.
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * Establece alumno en formación.
     * @param alumno
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
