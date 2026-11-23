package edu.CVAltair.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

        public Long getIdCV() {
                return idCV;
        }

        public void setIdCV(Long idCV) {
                this.idCV = idCV;
        }

        public Alumno getAlumno() {
                return alumno;
        }

        public void setAlumno(Alumno alumno) {
                this.alumno = alumno;
        }

        public List<CvExperiencia> getExperiencias() {
                return experiencias;
        }

        public void setExperiencias(List<CvExperiencia> experiencias) {
                this.experiencias = experiencias;
        }

        public List<CvFormacion> getFormaciones() {
                return formaciones;
        }

        public void setFormaciones(List<CvFormacion> formaciones) {
                this.formaciones = formaciones;
        }

        public List<CvHabilidad> getHabilidades() {
                return habilidades;
        }

        public void setHabilidades(List<CvHabilidad> habilidades) {
                this.habilidades = habilidades;
        }

        public List<CvIdioma> getIdiomas() {
                return idiomas;
        }

        public void setIdiomas(List<CvIdioma> idiomas) {
                this.idiomas = idiomas;
        }

        public List<CvCertificacion> getCertificaciones() {
                return certificaciones;
        }

        public void setCertificaciones(List<CvCertificacion> certificaciones) {
                this.certificaciones = certificaciones;
        }
}
