package edu.CVAltair.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "habilidades")
public class HabilidadTecnica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_habilidad_tecnica")
    private Long idHabilidadTecnica;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "destacada", nullable = false)
    private boolean destacada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;

    public HabilidadTecnica() {
    }

    public HabilidadTecnica(Long idHabilidadTecnica, String nombre, String descripcion, boolean destacada) {
        this.idHabilidadTecnica = idHabilidadTecnica;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.destacada = destacada;
    }


    public Long getIdHabilidadTecnica() {
        return idHabilidadTecnica;
    }

    public void setIdHabilidadTecnica(Long idHabilidadTecnica) {
        this.idHabilidadTecnica = idHabilidadTecnica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDestacada() {
        return destacada;
    }

    public void setDestacada(boolean destacada) {
        this.destacada = destacada;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
