package edu.CVAltair.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "cv_idiomas", uniqueConstraints = {
        @UniqueConstraint(name = "uk_cv_idioma", columnNames = {"id_cv", "id_idioma"})
})
/** Representa currículum (idioma) en la aplicación.
     *
     * @author ABR
     */
public class CvIdioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_cv_idioma")
    private Long idCvIdioma;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cv", nullable = false)
    private CV cv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_idioma", nullable = false)
    private Idioma idioma;


    @Column(name = "posicion", nullable = false, columnDefinition = "integer default 0")
    private int posicion;

    /**
     * Crea una instancia de currículum (idioma).
     */
    public CvIdioma() {
    }

    /**
     * Crea una instancia de currículum (idioma).
     * @param idCvIdioma
     * @param cv
     * @param idioma
     */
    public CvIdioma(Long idCvIdioma, CV cv, Idioma idioma) {
        this(idCvIdioma, cv, idioma, 0);
    }

    /**
     * Crea una instancia de currículum (idioma).
     * @param idCvIdioma
     * @param cv
     * @param idioma
     * @param posicion
     */
    public CvIdioma(Long idCvIdioma, CV cv, Idioma idioma, int posicion) {
        this.idCvIdioma = idCvIdioma;
        this.cv = cv;
        this.idioma = idioma;
        this.posicion = posicion;
    }

    /**
     * Obtiene identificador CV idioma de currículum (idioma).
     * @return resultado obtenido al ejecutar la operación.
     */
    public Long getIdCvIdioma() {
        return idCvIdioma;
    }

    /**
     * Establece identificador CV idioma en currículum (idioma).
     * @param idCvIdioma
     */
    public void setIdCvIdioma(Long idCvIdioma) {
        this.idCvIdioma = idCvIdioma;
    }

    /**
     * Obtiene CV de currículum (idioma).
     * @return resultado obtenido al ejecutar la operación.
     */
    public CV getCv() {
        return cv;
    }

    /**
     * Establece CV en currículum (idioma).
     * @param cv
     */
    public void setCv(CV cv) {
        this.cv = cv;
    }

    /**
     * Obtiene idioma de currículum (idioma).
     * @return resultado obtenido al ejecutar la operación.
     */
    public Idioma getIdioma() {
        return idioma;
    }

    /**
     * Establece idioma en currículum (idioma).
     * @param idioma
     */
    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    /**
     * Obtiene posicion de currículum (idioma).
     * @return resultado obtenido al ejecutar la operación.
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Establece posicion en currículum (idioma).
     * @param posicion
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
