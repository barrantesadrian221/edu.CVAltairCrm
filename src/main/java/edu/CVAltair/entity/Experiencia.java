package edu.CVAltair.entity;

import jakarta.persistence.*;


/**
 * Representa experiencia en la aplicación.
 * @author ABR
 */


@Entity

@Table(name = "experiencias")
public class Experiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_experiencia")
    private Long idExperiencia;

    @Column(name = "puesto", nullable = false, length = 100)
    private String puesto;

    @Column(name = "empresa", nullable = false, length = 100)
    private String empresa;

    @Column(name = "periodo", nullable = false, length = 50)
    private String periodo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;


    /**
     * Crea una instancia de experiencia.
     */
    public Experiencia() {
    }

    /**
     * Crea una instancia de experiencia.
     * @param idExperiencia
     * @param puesto
     * @param empresa
     * @param periodo
     * @param descripcion
     */
    public Experiencia(Long idExperiencia, String puesto, String empresa, String periodo, String descripcion) {
        this.idExperiencia = idExperiencia;
        this.puesto = puesto;
        this.empresa = empresa;
        this.periodo = periodo;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene identificador experiencia de experiencia.
     * @return resultado obtenido al ejecutar la operación.
     */
    public Long getIdExperiencia() {
        return idExperiencia;
    }

    /**
     * Establece identificador experiencia en experiencia.
     * @param idExperiencia
     */
    public void setIdExperiencia(Long idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    /**
     * Obtiene puesto de experiencia.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * Establece puesto en experiencia.
     * @param puesto
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * Obtiene empresa de experiencia.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Establece empresa en experiencia.
     * @param empresa
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * Obtiene periodo de experiencia.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * Establece periodo en experiencia.
     * @param periodo
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    /**
     * Obtiene descripcion de experiencia.
     * @return resultado obtenido al ejecutar la operación.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece descripcion en experiencia.
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene alumno de experiencia.
     * @return resultado obtenido al ejecutar la operación.
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * Establece alumno en experiencia.
     * @param alumno
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
