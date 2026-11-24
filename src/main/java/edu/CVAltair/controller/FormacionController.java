package edu.CVAltair.controller;

import edu.CVAltair.dto.FormacionRequestDto;
import edu.CVAltair.dto.FormacionResponseDto;
import edu.CVAltair.service.FormacionService;
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
 * Controlador REST para la gestión de formación.
 * @author ABR
 */
@RestController

@RequestMapping("/api/formaciones")
public class FormacionController {

    private final FormacionService formacionService;

    /**
     * Crea una instancia de formación.
     * @param formacionService
     */
    public FormacionController(FormacionService formacionService) {
        this.formacionService = formacionService;
    }

    /**
     * Ejecuta la operación «listar todas» para formación.
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping
    public ResponseEntity<List<FormacionResponseDto>> listarTodas() {
        return ResponseEntity.ok(formacionService.listarTodas());
    }

    /**
     * Busca formación por identificador.
     * @param id
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FormacionResponseDto> buscarPorId(@PathVariable Long id) {
        return formacionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Ejecuta la operación «listar por alumno» para formación.
     * @param idAlumno
     * @return respuesta HTTP con el resultado de la operación.
     */
    @GetMapping("/alumno/{idAlumno}")
    public ResponseEntity<List<FormacionResponseDto>> listarPorAlumno(@PathVariable Long idAlumno) {
        return ResponseEntity.ok(formacionService.listarPorAlumno(idAlumno));
    }

    /**
     * Crea formación con los datos proporcionados.
     * @param idAlumno
     * @param datos
     * @return respuesta HTTP con el resultado de la operación.
     */
    @PostMapping("/alumno/{idAlumno}")
    public ResponseEntity<FormacionResponseDto> crearParaAlumno(
            @PathVariable Long idAlumno,
            @RequestBody FormacionRequestDto datos) {
        FormacionResponseDto nuevaFormacion = formacionService.crearParaAlumno(idAlumno, datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFormacion);
    }

    /**
     * Actualiza formación con los datos proporcionados.
     * @param id
     * @param datos
     * @return respuesta HTTP con el resultado de la operación.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FormacionResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody FormacionRequestDto datos) {
        return ResponseEntity.ok(formacionService.actualizar(id, datos));
    }

    /**
     * Elimina formación según el criterio indicado.
     * @param id
     * @return respuesta HTTP con el resultado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        formacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
