package edu.CVAltair.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "certificaciones")
public class Certificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_certificacion")
    private Long idCertificacion;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "emision", nullable = false, length = 150)
    private String emision;

    @Column(name = "fecha_obtencion", nullable = false)
    private LocalDate fechaObtencion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;


    public Certificacion() {
    }

    public Certificacion(Long idCertificacion, String titulo, String emision, LocalDate fechaObtencion) {
        this.idCertificacion = idCertificacion;
        this.titulo = titulo;
        this.emision = emision;
        this.fechaObtencion = fechaObtencion;
    }


    public Long getIdCertificacion() {
        return idCertificacion;
    }

    public void setIdCertificacion(Long idCertificacion) {
        this.idCertificacion = idCertificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEmision() {
        return emision;
    }

    public void setEmision(String emision) {
        this.emision = emision;
    }

    public LocalDate getFechaObtencion() {
        return fechaObtencion;
    }

    public void setFechaObtencion(LocalDate fechaObtencion) {
        this.fechaObtencion = fechaObtencion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
