package edu.CVAltair.controller;

import edu.CVAltair.dto.CvContenidoRequestDto;
import edu.CVAltair.dto.CvFiltroRequestDto;
import edu.CVAltair.dto.CvResponseDto;
import edu.CVAltair.service.CVService;
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
@RequestMapping("/api/cvs")
public class CVController {

    private final CVService cvService;

    public CVController(CVService cvService) {
        this.cvService = cvService;
    }

    @PostMapping("/alumno/{idAlumno}")
    public ResponseEntity<CvResponseDto> crearParaAlumno(
            @PathVariable Long idAlumno,
            @RequestBody CvContenidoRequestDto contenido) {
        CvResponseDto nuevoCv = cvService.crearParaAlumno(idAlumno, contenido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCv);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CvResponseDto> buscarPorId(@PathVariable Long id) {
        return cvService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/alumno/{idAlumno}")
    public ResponseEntity<CvResponseDto> buscarPorAlumno(@PathVariable Long idAlumno) {
        return cvService.buscarPorAlumno(idAlumno)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CvResponseDto> actualizarContenido(
            @PathVariable Long id,
            @RequestBody CvContenidoRequestDto contenido) {
        return ResponseEntity.ok(cvService.actualizarContenido(id, contenido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cvService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<CvResponseDto>> filtrar(@RequestBody CvFiltroRequestDto filtros) {
        return ResponseEntity.ok(cvService.filtrar(filtros));
    }
}
