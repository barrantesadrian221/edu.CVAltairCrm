package edu.CVAltair.controller;

import edu.CVAltair.dto.CertificacionRequestDto;
import edu.CVAltair.dto.CertificacionResponseDto;
import edu.CVAltair.service.CertificacionService;
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
@RequestMapping("/api/certificaciones")
public class CertificacionController {

    private final CertificacionService certificacionService;

    public CertificacionController(CertificacionService certificacionService) {
        this.certificacionService = certificacionService;
    }

    @GetMapping
    public ResponseEntity<List<CertificacionResponseDto>> listarTodas() {
        return ResponseEntity.ok(certificacionService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificacionResponseDto> buscarPorId(@PathVariable Long id) {
        return certificacionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/alumno/{idAlumno}")
    public ResponseEntity<List<CertificacionResponseDto>> listarPorAlumno(@PathVariable Long idAlumno) {
        return ResponseEntity.ok(certificacionService.listarPorAlumno(idAlumno));
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<CertificacionResponseDto>> buscarPorTitulo(@RequestParam String texto) {
        return ResponseEntity.ok(certificacionService.buscarPorTitulo(texto));
    }

    @GetMapping("/emision")
    public ResponseEntity<List<CertificacionResponseDto>> buscarPorEmision(@RequestParam String texto) {
        return ResponseEntity.ok(certificacionService.buscarPorEmision(texto));
    }

    @PostMapping("/alumno/{idAlumno}")
    public ResponseEntity<CertificacionResponseDto> crearParaAlumno(
            @PathVariable Long idAlumno,
            @RequestBody CertificacionRequestDto datos) {
        CertificacionResponseDto nuevaCertificacion =
                certificacionService.crearParaAlumno(idAlumno, datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCertificacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificacionResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody CertificacionRequestDto datos) {
        return ResponseEntity.ok(certificacionService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        certificacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
