package edu.CVAltair.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/** Representa currículum en la aplicación.
 *
 * @author ABR
 */
@Entity
@Table(name = "cvs", uniqueConstraints = {
        @UniqueConstraint(name = "uk_cv_alumno", columnNames = "id_alumno")
})

public class CV {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Id_cv")
        private Long idCV;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_alumno")
        private Alumno alumno;

        @OneToMany(mappedBy = "cv", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
        private List<CvExperiencia> experiencias = new ArrayList<>();

        @OneToMany(mappedBy = "cv", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
        private List<CvFormacion> formaciones = new ArrayList<>();

        @OneToMany(mappedBy = "cv", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
        private List<CvHabilidad> habilidades = new ArrayList<>();

        @OneToMany(mappedBy = "cv", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
        private List<CvIdioma> idiomas = new ArrayList<>();

        @OneToMany(mappedBy = "cv", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
        private List<CvCertificacion> certificaciones = new ArrayList<>();

        /**
         * Obtiene identificador CV de currículum.
         * @return resultado obtenido al ejecutar la operación.
         */
        public Long getIdCV() {
                return idCV;
        }

        /**
         * Establece identificador CV en currículum.
         * @param idCV
         */
        public void setIdCV(Long idCV) {
                this.idCV = idCV;
        }

        /**
         * Obtiene alumno de currículum.
         * @return resultado obtenido al ejecutar la operación.
         */
        public Alumno getAlumno() {
                return alumno;
        }

        /**
         * Establece alumno en currículum.
         * @param alumno
         */
        public void setAlumno(Alumno alumno) {
                this.alumno = alumno;
        }

        /**
         * Obtiene experiencias de currículum.
         * @return lista con los registros obtenidos.
         */
        public List<CvExperiencia> getExperiencias() {
                return experiencias;
        }

        /**
         * Establece experiencias en currículum.
         * @param experiencias
         */
        public void setExperiencias(List<CvExperiencia> experiencias) {
                this.experiencias = experiencias;
        }

        /**
         * Obtiene formaciones de currículum.
         * @return lista con los registros obtenidos.
         */
        public List<CvFormacion> getFormaciones() {
                return formaciones;
        }

        /**
         * Establece formaciones en currículum.
         * @param formaciones
         */
        public void setFormaciones(List<CvFormacion> formaciones) {
                this.formaciones = formaciones;
        }

        /**
         * Obtiene habilidades de currículum.
         * @return lista con los registros obtenidos.
         */
        public List<CvHabilidad> getHabilidades() {
                return habilidades;
        }

        /**
         * Establece habilidades en currículum.
         * @param habilidades
         */
        public void setHabilidades(List<CvHabilidad> habilidades) {
                this.habilidades = habilidades;
        }

        /**
         * Obtiene idiomas de currículum.
         * @return lista con los registros obtenidos.
         */
        public List<CvIdioma> getIdiomas() {
                return idiomas;
        }

        /**
         * Establece idiomas en currículum.
         * @param idiomas
         */
        public void setIdiomas(List<CvIdioma> idiomas) {
                this.idiomas = idiomas;
        }

        /**
         * Obtiene certificaciones de currículum.
         * @return lista con los registros obtenidos.
         */
        public List<CvCertificacion> getCertificaciones() {
                return certificaciones;
        }

        /**
         * Establece certificaciones en currículum.
         * @param certificaciones
         */
        public void setCertificaciones(List<CvCertificacion> certificaciones) {
                this.certificaciones = certificaciones;
        }
}
