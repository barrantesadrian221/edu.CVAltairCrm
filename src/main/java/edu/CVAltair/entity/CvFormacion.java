package edu.CVAltair.entity;

import jakarta.persistence.*;

/** Representa currículum (formación) en la aplicación.
 *
 * @author ABR
 */
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

    /**
     * Crea una instancia de currículum (formación).
     */
    public CvFormacion() {
    }

    /**
     * Crea una instancia de currículum (formación).
     * @param idCvFormacion
     * @param cv
     * @param formacion
     */
    public CvFormacion(Long idCvFormacion, CV cv, Formacion formacion) {
        this(idCvFormacion, cv, formacion, 0);
    }

    /**
     * Crea una instancia de currículum (formación).
     * @param idCvFormacion
     * @param cv
     * @param formacion
     * @param posicion
     */
    public CvFormacion(Long idCvFormacion, CV cv, Formacion formacion, int posicion) {
        this.idCvFormacion = idCvFormacion;
        this.cv = cv;
        this.formacion = formacion;
        this.posicion = posicion;
    }

    /**
     * Obtiene identificador CV formación de currículum (formación).
     * @return resultado obtenido al ejecutar la operación.
     */
    public Long getIdCvFormacion() {
        return idCvFormacion;
    }

    /**
     * Establece identificador CV formación en currículum (formación).
     * @param idCvFormacion
     */
    public void setIdCvFormacion(Long idCvFormacion) {
        this.idCvFormacion = idCvFormacion;
    }

    /**
     * Obtiene CV de currículum (formación).
     * @return resultado obtenido al ejecutar la operación.
     */
    public CV getCv() {
        return cv;
    }

    /**
     * Establece CV en currículum (formación).
     * @param cv
     */
    public void setCv(CV cv) {
        this.cv = cv;
    }

    /**
     * Obtiene formación de currículum (formación).
     * @return resultado obtenido al ejecutar la operación.
     */
    public Formacion getFormacion() {
        return formacion;
    }

    /**
     * Establece formación en currículum (formación).
     * @param formacion
     */
    public void setFormacion(Formacion formacion) {
        this.formacion = formacion;
    }

    /**
     * Obtiene posicion de currículum (formación).
     * @return resultado obtenido al ejecutar la operación.
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Establece posicion en currículum (formación).
     * @param posicion
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
