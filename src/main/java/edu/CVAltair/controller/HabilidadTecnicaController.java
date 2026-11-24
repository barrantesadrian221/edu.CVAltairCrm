package edu.CVAltair.controller;

import edu.CVAltair.dto.HabilidadTecnicaRequestDto;
import edu.CVAltair.dto.HabilidadTecnicaResponseDto;
import edu.CVAltair.service.HabilidadTecnicaService;
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


/**
 * Controlador REST para la gestión de habilidad técnica.
 * @author ABR
 */
@RestController

@RequestMapping("/api/habilidades")
public class HabilidadTecnicaController {

    private final HabilidadTecnicaService habilidadService;

    /**
     * Crea una instancia de habilidad técnica.
     * @param habilidadService
     */
    public HabilidadTecnicaController(HabilidadTecnicaService habilidadService) {
        this.habilidadService = habilidadService;
    }

    /**
     * Ejecuta la operación «listar todas» para habilidad técnica.
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping
    public ResponseEntity<List<HabilidadTecnicaResponseDto>> listarTodas() {
        return ResponseEntity.ok(habilidadService.listarTodas());
    }

    /**
     * Busca habilidad técnica por identificador.
     * @param id
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HabilidadTecnicaResponseDto> buscarPorId(@PathVariable Long id) {
        return habilidadService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Ejecuta la operación «listar por alumno» para habilidad técnica.
     * @param idAlumno
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/alumno/{idAlumno}")
    public ResponseEntity<List<HabilidadTecnicaResponseDto>> listarPorAlumno(@PathVariable Long idAlumno) {
        return ResponseEntity.ok(habilidadService.listarPorAlumno(idAlumno));
    }

    /**
     * Busca habilidad técnica por nombre.
     * @param texto
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/nombre")
    public ResponseEntity<List<HabilidadTecnicaResponseDto>> buscarPorNombre(@RequestParam String texto) {
        return ResponseEntity.ok(habilidadService.buscarPorNombre(texto));
    }

    /**
     * Ejecuta la operación «listar por destacada» para habilidad técnica.
     * @param destacada
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/destacada/{destacada}")
    public ResponseEntity<List<HabilidadTecnicaResponseDto>> listarPorDestacada(
            @PathVariable boolean destacada) {
        return ResponseEntity.ok(habilidadService.listarPorDestacada(destacada));
    }

    /**
     * Crea habilidad técnica con los datos proporcionados.
     * @param idAlumno
     * @param datos
     * @return respuesta HTTP con el resultado de la operación.
     */
    @PostMapping("/alumno/{idAlumno}")
    public ResponseEntity<HabilidadTecnicaResponseDto> crearParaAlumno(
            @PathVariable Long idAlumno,
            @RequestBody HabilidadTecnicaRequestDto datos) {
        HabilidadTecnicaResponseDto nuevaHabilidad = habilidadService.crearParaAlumno(idAlumno, datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaHabilidad);
    }

    /**
     * Actualiza habilidad técnica con los datos proporcionados.
     * @param id
     * @param datos
     * @return respuesta HTTP con el resultado de la operación.
     */
    @PutMapping("/{id}")
    public ResponseEntity<HabilidadTecnicaResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody HabilidadTecnicaRequestDto datos) {
        return ResponseEntity.ok(habilidadService.actualizar(id, datos));
    }

    /**
     * Elimina habilidad técnica según el criterio indicado.
     * @param id
     * @return respuesta HTTP con el resultado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        habilidadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
