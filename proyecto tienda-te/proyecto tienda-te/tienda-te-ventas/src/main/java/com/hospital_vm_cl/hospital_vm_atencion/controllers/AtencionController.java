package com.hospital_vm_cl.hospital_vm_atencion.controllers;

import com.hospital_vm_cl.hospital_vm_atencion.dtos.request.AtencionRequest;
import com.hospital_vm_cl.hospital_vm_atencion.dtos.response.AtencionResponse;
import com.hospital_vm_cl.hospital_vm_atencion.services.AtencionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Expone los endpoints REST del microservicio de atenciones
@RestController
@RequestMapping("/api/v1/atenciones")
public class AtencionController {

    private final AtencionService atencionService;

    public AtencionController(AtencionService atencionService) {
        this.atencionService = atencionService;
    }

    // GET /api/v1/atenciones
    // Lista todas las atenciones
    @GetMapping
    public ResponseEntity<List<AtencionResponse>> listar() {
        return ResponseEntity.ok(atencionService.obtenerTodas());
    }

    // GET /api/v1/atenciones/{id}
    // Busca una atencion por su ID
    @GetMapping("/{id}")
    public ResponseEntity<AtencionResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(atencionService.obtenerPorId(id));
    }

    // GET /api/v1/atenciones/paciente/{idPaciente}
    // Lista las atenciones de un paciente específico
    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<AtencionResponse>> obtenerPorPaciente(@PathVariable Long idPaciente) {
        return ResponseEntity.ok(atencionService.obtenerPorPaciente(idPaciente));
    }

    // POST /api/v1/atenciones
    // Crea una nueva atencion validando el request
    @PostMapping
    public ResponseEntity<AtencionResponse> guardar(@Valid @RequestBody AtencionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(atencionService.guardar(request));
    }

    // PUT /api/v1/atenciones/{id}
    // Actualiza una atencion existente
    @PutMapping("/{id}")
    public ResponseEntity<AtencionResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtencionRequest request
    ) {
        return ResponseEntity.ok(atencionService.actualizar(id, request));
    }

    // DELETE /api/v1/atenciones/{id}
    // Elimina una atencion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        atencionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}