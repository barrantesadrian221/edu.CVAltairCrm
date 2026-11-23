package edu.CVAltair.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "experiencias")
public class Experiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_experiencia")
    private Long idExperiencia;

    @Column(name = "puesto", nullable = false, length = 100)
    private String puesto;

    @Column(name = "empresa", nullable = false, length = 100)
    private String empresa;

    @Column(name = "periodo", nullable = false, length = 50)
    private String periodo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;


    public Experiencia() {
    }

    public Experiencia(Long idExperiencia, String puesto, String empresa, String periodo, String descripcion) {
        this.idExperiencia = idExperiencia;
        this.puesto = puesto;
        this.empresa = empresa;
        this.periodo = periodo;
        this.descripcion = descripcion;
    }

    public Long getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(Long idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
