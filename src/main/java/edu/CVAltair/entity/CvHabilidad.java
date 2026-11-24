package edu.CVAltair.entity;

import jakarta.persistence.*;

/** Representa currículum (habilidad) en la aplicación.
 *
 * @author ABR
 */
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


    @Column(name = "posicion", nullable = false, columnDefinition = "integer default 0")
    private int posicion;

    /**
     * Crea una instancia de currículum (habilidad).
     */
    public CvHabilidad() {
    }

    /**
     * Crea una instancia de currículum (habilidad).
     * @param idCvHabilidad
     * @param cv
     * @param habilidad
     */
    public CvHabilidad(Long idCvHabilidad, CV cv, HabilidadTecnica habilidad) {
        this(idCvHabilidad, cv, habilidad, 0);
    }

    /**
     * Crea una instancia de currículum (habilidad).
     * @param idCvHabilidad
     * @param cv
     * @param habilidad
     * @param posicion
     */
    public CvHabilidad(Long idCvHabilidad, CV cv, HabilidadTecnica habilidad, int posicion) {
        this.idCvHabilidad = idCvHabilidad;
        this.cv = cv;
        this.habilidad = habilidad;
        this.posicion = posicion;
    }

    /**
     * Obtiene identificador CV habilidad de currículum (habilidad).
     * @return resultado obtenido al ejecutar la operación.
     */
    public Long getIdCvHabilidad() {
        return idCvHabilidad;
    }

    /**
     * Establece identificador CV habilidad en currículum (habilidad).
     * @param idCvHabilidad
     */
    public void setIdCvHabilidad(Long idCvHabilidad) {
        this.idCvHabilidad = idCvHabilidad;
    }

    /**
     * Obtiene CV de currículum (habilidad).
     * @return resultado obtenido al ejecutar la operación.
     */
    public CV getCv() {
        return cv;
    }

    /**
     * Establece CV en currículum (habilidad).
     * @param cv
     */
    public void setCv(CV cv) {
        this.cv = cv;
    }

    /**
     * Obtiene habilidad de currículum (habilidad).
     * @return resultado obtenido al ejecutar la operación.
     */
    public HabilidadTecnica getHabilidad() {
        return habilidad;
    }

    /**
     * Establece habilidad en currículum (habilidad).
     * @param habilidad
     */
    public void setHabilidad(HabilidadTecnica habilidad) {
        this.habilidad = habilidad;
    }

    /**
     * Obtiene posicion de currículum (habilidad).
     * @return resultado obtenido al ejecutar la operación.
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Establece posicion en currículum (habilidad).
     * @param posicion
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
