package edu.CVAltair.entity;

import jakarta.persistence.*;


/** Representa currículum (certificación) en la aplicación.
 *
 * @author ABR
 */
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

    /**
     * Crea una instancia de currículum (certificación).
     */
    public CvCertificacion() {
    }

    /**
     * Crea una instancia de currículum (certificación).
     * @param idCvCertificacion
     * @param cv
     * @param certificacion
     */
    public CvCertificacion(Long idCvCertificacion, CV cv, Certificacion certificacion) {
        this(idCvCertificacion, cv, certificacion, 0);
    }

    /**
     * Crea una instancia de currículum (certificación).
     * @param idCvCertificacion
     * @param cv
     * @param certificacion
     * @param posicion
     */
    public CvCertificacion(Long idCvCertificacion, CV cv, Certificacion certificacion, int posicion) {
        this.idCvCertificacion = idCvCertificacion;
        this.cv = cv;
        this.certificacion = certificacion;
        this.posicion = posicion;
    }

    /**
     * Obtiene identificador CV certificación de currículum (certificación).
     * @return resultado obtenido al ejecutar la operación.
     */
    public Long getIdCvCertificacion() {
        return idCvCertificacion;
    }

    /**
     * Establece identificador CV certificación en currículum (certificación).
     * @param idCvCertificacion
     */
    public void setIdCvCertificacion(Long idCvCertificacion) {
        this.idCvCertificacion = idCvCertificacion;
    }

    /**
     * Obtiene CV de currículum (certificación).
     * @return resultado obtenido al ejecutar la operación.
     */
    public CV getCv() {
        return cv;
    }

    /**
     * Establece CV en currículum (certificación).
     * @param cv
     */
    public void setCv(CV cv) {
        this.cv = cv;
    }

    /**
     * Obtiene certificación de currículum (certificación).
     * @return resultado obtenido al ejecutar la operación.
     */
    public Certificacion getCertificacion() {
        return certificacion;
    }

    /**
     * Establece certificación en currículum (certificación).
     * @param certificacion
     */
    public void setCertificacion(Certificacion certificacion) {
        this.certificacion = certificacion;
    }

    /**
     * Obtiene posicion de currículum (certificación).
     * @return resultado obtenido al ejecutar la operación.
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Establece posicion en currículum (certificación).
     * @param posicion
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
