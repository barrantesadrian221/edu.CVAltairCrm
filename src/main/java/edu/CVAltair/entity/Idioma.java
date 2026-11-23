package edu.CVAltair.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "idiomas")
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_idioma")
    private Long idIdioma;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "nivel", nullable = false, length = 50)
    private String nivel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;


    public Idioma() {
    }

    public Idioma(Long idIdioma, String nombre, String nivel) {
        this.idIdioma = idIdioma;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public Long getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Long idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
