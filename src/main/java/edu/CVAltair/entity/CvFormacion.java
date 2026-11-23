package edu.CVAltair.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cv_formaciones", uniqueConstraints = {
        @UniqueConstraint(name = "uk_cv_formacion", columnNames = {"id_cv", "id_formacion"})
})
public class CvFormacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_cv_formacion")
    private Long idCvFormacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cv", nullable = false)
    private CV cv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_formacion", nullable = false)
    private Formacion formacion;

    @Column(name = "posicion", nullable = false, columnDefinition = "integer default 0")
    private int posicion;

    public CvFormacion() {
    }

    public CvFormacion(Long idCvFormacion, CV cv, Formacion formacion) {
        this(idCvFormacion, cv, formacion, 0);
    }

    public CvFormacion(Long idCvFormacion, CV cv, Formacion formacion, int posicion) {
        this.idCvFormacion = idCvFormacion;
        this.cv = cv;
        this.formacion = formacion;
        this.posicion = posicion;
    }

    public Long getIdCvFormacion() {
        return idCvFormacion;
    }

    public void setIdCvFormacion(Long idCvFormacion) {
        this.idCvFormacion = idCvFormacion;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }

    public Formacion getFormacion() {
        return formacion;
    }

    public void setFormacion(Formacion formacion) {
        this.formacion = formacion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
