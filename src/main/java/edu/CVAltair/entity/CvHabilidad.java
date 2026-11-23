package edu.CVAltair.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cv_habilidades", uniqueConstraints = {
        @UniqueConstraint(name = "uk_cv_habilidad", columnNames = {"id_cv", "id_habilidad_tecnica"})
})
public class CvHabilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_cv_habilidad")
    private Long idCvHabilidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cv", nullable = false)
    private CV cv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_habilidad_tecnica", nullable = false)
    private HabilidadTecnica habilidad;

    public CvHabilidad() {
    }

    public CvHabilidad(Long idCvHabilidad, CV cv, HabilidadTecnica habilidad) {
        this.idCvHabilidad = idCvHabilidad;
        this.cv = cv;
        this.habilidad = habilidad;
    }

    public Long getIdCvHabilidad() {
        return idCvHabilidad;
    }

    public void setIdCvHabilidad(Long idCvHabilidad) {
        this.idCvHabilidad = idCvHabilidad;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }

    public HabilidadTecnica getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(HabilidadTecnica habilidad) {
        this.habilidad = habilidad;
    }
}
