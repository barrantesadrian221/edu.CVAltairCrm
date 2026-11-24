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


/**
 * Controlador REST para la gestión de currículum.
 * @author ABR
 */
@RestController

@RequestMapping("/api/cvs")
public class CVController {

    private final CVService cvService;

    /**
     * Crea una instancia de currículum.
     * @param cvService
     */
    public CVController(CVService cvService) {
        this.cvService = cvService;
    }

    /**
     * Crea currículum con los datos proporcionados.
     * @param idAlumno
     * @param contenido
     * @return respuesta HTTP con el resultado de la operación.
     */
    @PostMapping("/alumno/{idAlumno}")
    public ResponseEntity<CvResponseDto> crearParaAlumno(
            @PathVariable Long idAlumno,
            @RequestBody CvContenidoRequestDto contenido) {
        CvResponseDto nuevoCv = cvService.crearParaAlumno(idAlumno, contenido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCv);
    }

    /**
     * Busca currículum por identificador.
     * @param id
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CvResponseDto> buscarPorId(@PathVariable Long id) {
        return cvService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Busca currículum por alumno.
     * @param idAlumno
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/alumno/{idAlumno}")
    public ResponseEntity<CvResponseDto> buscarPorAlumno(@PathVariable Long idAlumno) {
        return cvService.buscarPorAlumno(idAlumno)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Actualiza currículum con los datos proporcionados.
     * @param id
     * @param contenido
     * @return respuesta HTTP con el resultado de la operación.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CvResponseDto> actualizarContenido(
            @PathVariable Long id,
            @RequestBody CvContenidoRequestDto contenido) {
        return ResponseEntity.ok(cvService.actualizarContenido(id, contenido));
    }

    /**
     * Elimina currículum según el criterio indicado.
     * @param id
     * @return respuesta HTTP con el resultado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cvService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Ejecuta la operación «filtrar» para currículum.
     * @param filtros
     * @return respuesta HTTP con el resultado de la operación.
     */
    @PostMapping("/buscar")
    public ResponseEntity<List<CvResponseDto>> filtrar(@RequestBody CvFiltroRequestDto filtros) {
        return ResponseEntity.ok(cvService.filtrar(filtros));
    }
}
