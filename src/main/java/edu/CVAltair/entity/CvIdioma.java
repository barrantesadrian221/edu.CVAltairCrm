package edu.CVAltair.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cv_idiomas", uniqueConstraints = {
        @UniqueConstraint(name = "uk_cv_idioma", columnNames = {"id_cv", "id_idioma"})
})
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

    public CvIdioma() {
    }

    public CvIdioma(Long idCvIdioma, CV cv, Idioma idioma) {
        this.idCvIdioma = idCvIdioma;
        this.cv = cv;
        this.idioma = idioma;
    }

    public Long getIdCvIdioma() {
        return idCvIdioma;
    }

    public void setIdCvIdioma(Long idCvIdioma) {
        this.idCvIdioma = idCvIdioma;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }
}
