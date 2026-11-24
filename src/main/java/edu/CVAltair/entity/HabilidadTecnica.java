package edu.CVAltair.entity;

import jakarta.persistence.*;


/**
 * Representa habilidad técnica en la aplicación.
 * @author ABR
 */
@Entity
@Table(name = "habilidades")
public class HabilidadTecnica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_habilidad_tecnica")
    private Long idHabilidadTecnica;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "destacada", nullable = false)
    private boolean destacada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;

    /**
     * Crea una instancia de habilidad técnica.
     */
    public HabilidadTecnica() {
    }

    /**
     * Crea una instancia de habilidad técnica.
     * @param idHabilidadTecnica
     * @param nombre
     * @param descripcion
     * @param destacada
     */
    public HabilidadTecnica(Long idHabilidadTecnica, String nombre, String descripcion, boolean destacada) {
        this.idHabilidadTecnica = idHabilidadTecnica;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.destacada = destacada;
    }


    /**
     * Obtiene identificador habilidad técnica de habilidad técnica.
     * @return resultado obtenido al ejecutar la operación.
     */
    public Long getIdHabilidadTecnica() {
        return idHabilidadTecnica;
    }

    /**
     * Establece identificador habilidad técnica en habilidad técnica.
     * @param idHabilidadTecnica
     */
    public void setIdHabilidadTecnica(Long idHabilidadTecnica) {
        this.idHabilidadTecnica = idHabilidadTecnica;
    }

    /**
     * Obtiene nombre de habilidad técnica.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece nombre en habilidad técnica.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene descripcion de habilidad técnica.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece descripcion en habilidad técnica.
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene destacada de habilidad técnica.
     * @return true si se cumple la condición comprobada; false en caso contrario.
     */
    public boolean isDestacada() {
        return destacada;
    }

    /**
     * Establece destacada en habilidad técnica.
     * @param destacada
     */
    public void setDestacada(boolean destacada) {
        this.destacada = destacada;
    }

    /**
     * Obtiene alumno de habilidad técnica.
     * @return resultado obtenido al ejecutar la operación.
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * Establece alumno en habilidad técnica.
     * @param alumno
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
