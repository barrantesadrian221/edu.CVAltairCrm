package edu.CVAltair.controller;

import edu.CVAltair.dto.AlumnoRequestDto;
import edu.CVAltair.dto.AlumnoResponseDto;
import edu.CVAltair.dto.TutorialResponseDto;
import edu.CVAltair.service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Controlador REST para la gestión de alumno.
 * @author ABR
 */
@RestController

@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    /**
     *  Crea una instancia de alumno
     * @param alumnoService
     */
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    /**
     * Devuelve todos los registros de alumnos.
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping
    public ResponseEntity<List<AlumnoResponseDto>> listarTodos() {
        return ResponseEntity.ok(alumnoService.listarTodos());
    }

    /**
     * Busca alumno por identificador.
     * @param id
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponseDto> buscarPorId(@PathVariable Long id) {
        return alumnoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Busca alumno por DNI.
     * @param dni
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/dni/{dni}")
    public ResponseEntity<AlumnoResponseDto> buscarPorDni(@PathVariable String dni) {
        return alumnoService.buscarPorDni(dni)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Busca alumno por correo electrónico.
     * @param email
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<AlumnoResponseDto> buscarPorEmail(@PathVariable String email) {
        return alumnoService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Busca alumno por número seguridad social.
     * @param numeroSeguridadSocial
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/nss/{numeroSeguridadSocial}")
    public ResponseEntity<AlumnoResponseDto> buscarPorNumeroSeguridadSocial(
            @PathVariable String numeroSeguridadSocial) {
        return alumnoService.buscarPorNumeroSeguridadSocial(numeroSeguridadSocial)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Consulta el tutorial disponible para el alumno.
     * Implementado con ayuda de IA.
     *
     * @param id
     * @return estado y pasos del tutorial
     */
    @GetMapping("/{id}/tutorial")
    public ResponseEntity<TutorialResponseDto> consultarTutorial(@PathVariable Long id) {
        return ResponseEntity.ok(alumnoService.consultarTutorial(id));
    }

    /**
     * Marca el tutorial como completado.
     * Implementado con ayuda de IA.
     *
     * @param id
     * @return estado actualizado del tutorial
     */
    @PostMapping("/{id}/tutorial/completar")
    public ResponseEntity<TutorialResponseDto> completarTutorial(@PathVariable Long id) {
        return ResponseEntity.ok(alumnoService.completarTutorial(id));
    }

    /**
     * Marca el tutorial como omitido.
     * Implementado con ayuda de IA.
     *
     * @param id
     * @return estado actualizado del tutorial
     */
    @PostMapping("/{id}/tutorial/omitir")
    public ResponseEntity<TutorialResponseDto> omitirTutorial(@PathVariable Long id) {
        return ResponseEntity.ok(alumnoService.omitirTutorial(id));
    }

    /**
     * Crea alumno con los datos proporcionados.
     * @param alumnoRequestDto
     * @return respuesta HTTP con el resultado de la operación.
     */
    @PostMapping
    public ResponseEntity<AlumnoResponseDto> crear(@RequestBody AlumnoRequestDto alumnoRequestDto) {
        AlumnoResponseDto nuevoAlumno = alumnoService.crear(alumnoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlumno);
    }

    /**
     * Actualiza alumno con los datos proporcionados.
     * @param id
     * @param alumnoRequestDto
     * @return respuesta HTTP con el resultado de la operación.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AlumnoResponseDto> actualizarPorId(
            @PathVariable Long id,
            @RequestBody AlumnoRequestDto alumnoRequestDto) {
        return ResponseEntity.ok(alumnoService.actualizarPorId(id, alumnoRequestDto));
    }

    /**
     * Actualiza alumno con los datos proporcionados.
     * @param dni
     * @param alumnoRequestDto
     * @return respuesta HTTP con el resultado de la operación.
     */
    @PutMapping("/dni/{dni}")
    public ResponseEntity<AlumnoResponseDto> actualizarPorDni(
            @PathVariable String dni,
            @RequestBody AlumnoRequestDto alumnoRequestDto) {
        return ResponseEntity.ok(alumnoService.actualizarPorDni(dni, alumnoRequestDto));
    }

    /**
     * Elimina alumno según el criterio indicado.
     * @param id
     * @return respuesta HTTP con el resultado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        alumnoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Elimina alumno según el criterio indicado.
     * @param dni
     * @return respuesta HTTP con el resultado de la operación.
     */
    @DeleteMapping("/dni/{dni}")
    public ResponseEntity<Void> eliminarPorDni(@PathVariable String dni) {
        alumnoService.eliminarPorDni(dni);
        return ResponseEntity.noContent().build();
    }
}
