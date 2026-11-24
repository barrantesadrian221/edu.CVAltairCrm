package edu.CVAltair.entity;

import jakarta.persistence.*;


/**
 * @author ABR
 *  Representa idioma en la aplicación.
 */
@Entity

@Table(name = "idiomas")
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_idioma")
    private Long idIdioma;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "nivel", nullable = false, length = 50)
    private String nivel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;


    /**
     * Crea una instancia de idioma.
     */
    public Idioma() {
    }

    /**
     * Crea una instancia de idioma.
     * @param idIdioma
     * @param nombre
     * @param nivel
     */
    public Idioma(Long idIdioma, String nombre, String nivel) {
        this.idIdioma = idIdioma;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    /**
     * Obtiene identificador idioma de idioma.
     * @return resultado obtenido al ejecutar la operación.
     */
    public Long getIdIdioma() {
        return idIdioma;
    }

    /**
     * Establece identificador idioma en idioma.
     * @param idIdioma
     */
    public void setIdIdioma(Long idIdioma) {
        this.idIdioma = idIdioma;
    }

    /**
     * Obtiene nombre de idioma.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece nombre en idioma.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene nivel de idioma.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * Establece nivel en idioma.
     * @param nivel
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * Obtiene alumno de idioma.
     * @return resultado obtenido al ejecutar la operación.
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * Establece alumno en idioma.
     * @param alumno
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
