package edu.CVAltair.controller;

import edu.CVAltair.dto.AlumnoRequestDto;
import edu.CVAltair.dto.AlumnoResponseDto;
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

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public ResponseEntity<List<AlumnoResponseDto>> listarTodos() {
        return ResponseEntity.ok(alumnoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponseDto> buscarPorId(@PathVariable Long id) {
        return alumnoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<AlumnoResponseDto> buscarPorDni(@PathVariable String dni) {
        return alumnoService.buscarPorDni(dni)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AlumnoResponseDto> buscarPorEmail(@PathVariable String email) {
        return alumnoService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nss/{numeroSeguridadSocial}")
    public ResponseEntity<AlumnoResponseDto> buscarPorNumeroSeguridadSocial(
            @PathVariable String numeroSeguridadSocial) {
        return alumnoService.buscarPorNumeroSeguridadSocial(numeroSeguridadSocial)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlumnoResponseDto> crear(@RequestBody AlumnoRequestDto alumnoRequestDto) {
        AlumnoResponseDto nuevoAlumno = alumnoService.crear(alumnoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoResponseDto> actualizarPorId(
            @PathVariable Long id,
            @RequestBody AlumnoRequestDto alumnoRequestDto) {
        return ResponseEntity.ok(alumnoService.actualizarPorId(id, alumnoRequestDto));
    }

    @PutMapping("/dni/{dni}")
    public ResponseEntity<AlumnoResponseDto> actualizarPorDni(
            @PathVariable String dni,
            @RequestBody AlumnoRequestDto alumnoRequestDto) {
        return ResponseEntity.ok(alumnoService.actualizarPorDni(dni, alumnoRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        alumnoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/dni/{dni}")
    public ResponseEntity<Void> eliminarPorDni(@PathVariable String dni) {
        alumnoService.eliminarPorDni(dni);
        return ResponseEntity.noContent().build();
    }
}
