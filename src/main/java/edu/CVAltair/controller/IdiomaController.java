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


/**
 * Controlador REST para la gestión de idioma.
 * @author ABR
 */
@RestController

@RequestMapping("/api/idiomas")
public class IdiomaController {

    private final IdiomaService idiomaService;

    /**
     * Crea una instancia de idioma.
     * @param idiomaService
     */
    public IdiomaController(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    /**
     * Devuelve todos los registros de idiomas.
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping
    public ResponseEntity<List<IdiomaResponseDto>> listarTodos() {
        return ResponseEntity.ok(idiomaService.listarTodos());
    }

    /**
     * Busca idioma por identificador.
     * @param id
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/{id}")
    public ResponseEntity<IdiomaResponseDto> buscarPorId(@PathVariable Long id) {
        return idiomaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Ejecuta la operación «listar por alumno» para idioma.
     * @param idAlumno
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/alumno/{idAlumno}")
    public ResponseEntity<List<IdiomaResponseDto>> listarPorAlumno(@PathVariable Long idAlumno) {
        return ResponseEntity.ok(idiomaService.listarPorAlumno(idAlumno));
    }

    /**
     * Busca idioma por nombre.
     * @param texto
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/nombre")
    public ResponseEntity<List<IdiomaResponseDto>> buscarPorNombre(@RequestParam String texto) {
        return ResponseEntity.ok(idiomaService.buscarPorNombre(texto));
    }

    /**
     * Busca idioma por nombre y nivel.
     * @param nombre
     * @param nivel
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<IdiomaResponseDto>> buscarPorNombreYNivel(
            @RequestParam String nombre,
            @RequestParam String nivel) {
        return ResponseEntity.ok(idiomaService.buscarPorNombreYNivel(nombre, nivel));
    }

    /**
     * Crea idioma con los datos proporcionados.
     * @param idAlumno
     * @param datos
     * @return respuesta HTTP con el resultado de la operación.
     */
    @PostMapping("/alumno/{idAlumno}")
    public ResponseEntity<IdiomaResponseDto> crearParaAlumno(
            @PathVariable Long idAlumno,
            @RequestBody IdiomaRequestDto datos) {
        IdiomaResponseDto nuevoIdioma = idiomaService.crearParaAlumno(idAlumno, datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoIdioma);
    }

    /**
     * Actualiza idioma con los datos proporcionados.
     * @param id
     * @param datos
     * @return respuesta HTTP con el resultado de la operación.
     */
    @PutMapping("/{id}")
    public ResponseEntity<IdiomaResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody IdiomaRequestDto datos) {
        return ResponseEntity.ok(idiomaService.actualizar(id, datos));
    }

    /**
     * Elimina idioma según el criterio indicado.
     * @param id
     * @return respuesta HTTP con el resultado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        idiomaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
