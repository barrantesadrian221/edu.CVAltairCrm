package edu.CVAltair.controller;

import edu.CVAltair.dto.ExperienciaRequestDto;
import edu.CVAltair.dto.ExperienciaResponseDto;
import edu.CVAltair.service.ExperienciaService;
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
@RequestMapping("/api/experiencias")
public class ExperienciaController {

    private final ExperienciaService experienciaService;

    public ExperienciaController(ExperienciaService experienciaService) {
        this.experienciaService = experienciaService;
    }

    @GetMapping
    public ResponseEntity<List<ExperienciaResponseDto>> listarTodas() {
        return ResponseEntity.ok(experienciaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienciaResponseDto> buscarPorId(@PathVariable Long id) {
        return experienciaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/alumno/{idAlumno}")
    public ResponseEntity<List<ExperienciaResponseDto>> listarPorAlumno(@PathVariable Long idAlumno) {
        return ResponseEntity.ok(experienciaService.listarPorAlumno(idAlumno));
    }

    @PostMapping("/alumno/{idAlumno}")
    public ResponseEntity<ExperienciaResponseDto> crearParaAlumno(
            @PathVariable Long idAlumno,
            @RequestBody ExperienciaRequestDto datos) {
        ExperienciaResponseDto nuevaExperiencia = experienciaService.crearParaAlumno(idAlumno, datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaExperiencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienciaResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody ExperienciaRequestDto datos) {
        return ResponseEntity.ok(experienciaService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        experienciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
