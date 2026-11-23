package edu.CVAltair.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cv_experiencias", uniqueConstraints = {
        @UniqueConstraint(name = "uk_cv_experiencia", columnNames = {"id_cv", "id_experiencia"})
})
public class CvExperiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_cv_experiencia")
    private Long idCvExperiencia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cv", nullable = false)
    private CV cv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_experiencia", nullable = false)
    private Experiencia experiencia;

    @Column(name = "posicion", nullable = false, columnDefinition = "integer default 0")
    private int posicion;

    public CvExperiencia() {
    }

    public CvExperiencia(Long idCvExperiencia, CV cv, Experiencia experiencia) {
        this(idCvExperiencia, cv, experiencia, 0);
    }

    public CvExperiencia(Long idCvExperiencia, CV cv, Experiencia experiencia, int posicion) {
        this.idCvExperiencia = idCvExperiencia;
        this.cv = cv;
        this.experiencia = experiencia;
        this.posicion = posicion;
    }

    public Long getIdCvExperiencia() {
        return idCvExperiencia;
    }

    public void setIdCvExperiencia(Long idCvExperiencia) {
        this.idCvExperiencia = idCvExperiencia;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
