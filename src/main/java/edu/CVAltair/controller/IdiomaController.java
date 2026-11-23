package edu.CVAltair.controller;

import edu.CVAltair.dto.IdiomaRequestDto;
import edu.CVAltair.dto.IdiomaResponseDto;
import edu.CVAltair.service.IdiomaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/idiomas")
public class IdiomaController {

    private final IdiomaService idiomaService;

    public IdiomaController(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    @GetMapping
    public ResponseEntity<List<IdiomaResponseDto>> listarTodos() {
        return ResponseEntity.ok(idiomaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdiomaResponseDto> buscarPorId(@PathVariable Long id) {
        return idiomaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/alumno/{idAlumno}")
    public ResponseEntity<List<IdiomaResponseDto>> listarPorAlumno(@PathVariable Long idAlumno) {
        return ResponseEntity.ok(idiomaService.listarPorAlumno(idAlumno));
    }

    @GetMapping("/nombre")
    public ResponseEntity<List<IdiomaResponseDto>> buscarPorNombre(@RequestParam String texto) {
        return ResponseEntity.ok(idiomaService.buscarPorNombre(texto));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<IdiomaResponseDto>> buscarPorNombreYNivel(
            @RequestParam String nombre,
            @RequestParam String nivel) {
        return ResponseEntity.ok(idiomaService.buscarPorNombreYNivel(nombre, nivel));
    }

    @PostMapping("/alumno/{idAlumno}")
    public ResponseEntity<IdiomaResponseDto> crearParaAlumno(
            @PathVariable Long idAlumno,
            @RequestBody IdiomaRequestDto datos) {
        IdiomaResponseDto nuevoIdioma = idiomaService.crearParaAlumno(idAlumno, datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoIdioma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdiomaResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody IdiomaRequestDto datos) {
        return ResponseEntity.ok(idiomaService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        idiomaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
