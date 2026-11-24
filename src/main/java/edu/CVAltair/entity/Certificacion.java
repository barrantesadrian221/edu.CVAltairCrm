package edu.CVAltair.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


/**
 * @author ABR
  * Representa certificación en la aplicación.
 */
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


    /**
     * Crea una instancia de certificación.
     */
    public Certificacion() {
    }

    /**
     * Crea una instancia de certificación.
     * @param idCertificacion
     * @param titulo
     * @param emision
     * @param fechaObtencion
     */
    public Certificacion(Long idCertificacion, String titulo, String emision, LocalDate fechaObtencion) {
        this.idCertificacion = idCertificacion;
        this.titulo = titulo;
        this.emision = emision;
        this.fechaObtencion = fechaObtencion;
    }


    /**
     * Obtiene identificador certificación de certificación.
     * @return resultado obtenido al ejecutar la operación.
     */
    public Long getIdCertificacion() {
        return idCertificacion;
    }

    /**
     * Establece identificador certificación en certificación.
     * @param idCertificacion
     */
    public void setIdCertificacion(Long idCertificacion) {
        this.idCertificacion = idCertificacion;
    }

    /**
     * Obtiene titulo de certificación.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece titulo en certificación.
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene emision de certificación.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getEmision() {
        return emision;
    }

    /**
     * Establece emision en certificación.
     * @param emision
     */
    public void setEmision(String emision) {
        this.emision = emision;
    }

    /**
     * Obtiene fecha obtencion de certificación.
     * @return resultado obtenido al ejecutar la operación.
     */
    public LocalDate getFechaObtencion() {
        return fechaObtencion;
    }

    /**
     * Establece fecha obtencion en certificación.
     * @param fechaObtencion
     */
    public void setFechaObtencion(LocalDate fechaObtencion) {
        this.fechaObtencion = fechaObtencion;
    }

    /**
     * Obtiene alumno de certificación.
     * @return resultado obtenido al ejecutar la operación.
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * Establece alumno en certificación.
     * @param alumno
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
