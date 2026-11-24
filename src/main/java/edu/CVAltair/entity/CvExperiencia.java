package edu.CVAltair.entity;

import jakarta.persistence.*;

/** Representa currículum (experiencia) en la aplicación.
 *
 * @author ABR
 */
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

    /**
     * Crea una instancia de currículum (experiencia).
     */
    public CvExperiencia() {
    }

    /**
     * Crea una instancia de currículum (experiencia).
     * @param idCvExperiencia
     * @param cv
     * @param experiencia
     */
    public CvExperiencia(Long idCvExperiencia, CV cv, Experiencia experiencia) {
        this(idCvExperiencia, cv, experiencia, 0);
    }

    /**
     * Crea una instancia de currículum (experiencia).
     * @param idCvExperiencia
     * @param cv
     * @param experiencia
     * @param posicion
     */
    public CvExperiencia(Long idCvExperiencia, CV cv, Experiencia experiencia, int posicion) {
        this.idCvExperiencia = idCvExperiencia;
        this.cv = cv;
        this.experiencia = experiencia;
        this.posicion = posicion;
    }

    /**
     * Obtiene identificador CV experiencia de currículum (experiencia).
     * @return resultado obtenido al ejecutar la operación.
     */
    public Long getIdCvExperiencia() {
        return idCvExperiencia;
    }

    /**
     * Establece identificador CV experiencia en currículum (experiencia).
     * @param idCvExperiencia
     */
    public void setIdCvExperiencia(Long idCvExperiencia) {
        this.idCvExperiencia = idCvExperiencia;
    }

    /**
     * Obtiene CV de currículum (experiencia).
     * @return resultado obtenido al ejecutar la operación.
     */
    public CV getCv() {
        return cv;
    }

    /**
     * Establece CV en currículum (experiencia).
     * @param cv
     */
    public void setCv(CV cv) {
        this.cv = cv;
    }

    /**
     * Obtiene experiencia de currículum (experiencia).
     * @return resultado obtenido al ejecutar la operación.
     */
    public Experiencia getExperiencia() {
        return experiencia;
    }

    /**
     * Establece experiencia en currículum (experiencia).
     * @param experiencia
     */
    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }

    /**
     * Obtiene posicion de currículum (experiencia).
     * @return resultado obtenido al ejecutar la operación.
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Establece posicion en currículum (experiencia).
     * @param posicion
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
