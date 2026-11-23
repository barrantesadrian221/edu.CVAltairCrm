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

    public CvCertificacion() {
    }

    public CvCertificacion(Long idCvCertificacion, CV cv, Certificacion certificacion) {
        this.idCvCertificacion = idCvCertificacion;
        this.cv = cv;
        this.certificacion = certificacion;
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
}
