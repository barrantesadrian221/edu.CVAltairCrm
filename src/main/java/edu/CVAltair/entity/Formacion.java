package edu.CVAltair.entity;

import jakarta.persistence.*;

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


    public Formacion() {
    }

    public Formacion(Long idFormacion, String titulo, String centro, String periodo, boolean enCurso) {
        this.idFormacion = idFormacion;
        this.titulo = titulo;
        this.centro = centro;
        this.periodo = periodo;
        this.enCurso = enCurso;
    }

    public Long getIdFormacion() {
        return idFormacion;
    }

    public void setIdFormacion(Long idFormacion) {
        this.idFormacion = idFormacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public boolean isEnCurso() {
        return enCurso;
    }

    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
