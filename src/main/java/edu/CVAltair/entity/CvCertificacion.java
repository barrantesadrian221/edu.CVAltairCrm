package edu.CVAltair.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cv_certificaciones", uniqueConstraints = {
        @UniqueConstraint(name = "uk_cv_certificacion", columnNames = {"id_cv", "id_certificacion"})
})
public class CvCertificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_cv_certificacion")
    private Long idCvCertificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cv", nullable = false)
    private CV cv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_certificacion", nullable = false)
    private Certificacion certificacion;

    @Column(name = "posicion", nullable = false, columnDefinition = "integer default 0")
    private int posicion;

    public CvCertificacion() {
    }

    public CvCertificacion(Long idCvCertificacion, CV cv, Certificacion certificacion) {
        this(idCvCertificacion, cv, certificacion, 0);
    }

    public CvCertificacion(Long idCvCertificacion, CV cv, Certificacion certificacion, int posicion) {
        this.idCvCertificacion = idCvCertificacion;
        this.cv = cv;
        this.certificacion = certificacion;
        this.posicion = posicion;
    }

    public Long getIdCvCertificacion() {
        return idCvCertificacion;
    }

    public void setIdCvCertificacion(Long idCvCertificacion) {
        this.idCvCertificacion = idCvCertificacion;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }

    public Certificacion getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(Certificacion certificacion) {
        this.certificacion = certificacion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
