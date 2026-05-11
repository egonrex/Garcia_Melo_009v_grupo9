package com.hospital_vm_cl.hospital_vm.controllers;

import com.hospital_vm_cl.hospital_vm.models.PacienteModel;
import com.hospital_vm_cl.hospital_vm.services.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<PacienteModel> listar() {
        return pacienteService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteModel> obtenerPorId(@PathVariable Long id) {
        Optional<PacienteModel> paciente = pacienteService.obtenerPorId(id);
        return paciente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<PacienteModel> obtenerPorRut(@PathVariable String rut) {
        Optional<PacienteModel> paciente = pacienteService.obtenerPorRut(rut);
        return paciente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PacienteModel> guardar(@RequestBody PacienteModel paciente) {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}