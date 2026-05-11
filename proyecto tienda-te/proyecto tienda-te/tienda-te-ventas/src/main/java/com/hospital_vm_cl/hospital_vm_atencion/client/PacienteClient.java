package com.hospital_vm_cl.hospital_vm_atencion.client;

import com.hospital_vm_cl.hospital_vm_atencion.config.FeignConfig;
import com.hospital_vm_cl.hospital_vm_atencion.dtos.response.PacienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Cliente Feign para consumir el microservicio de pacientes
@FeignClient(
        name = "paciente-client",
        url = "${paciente.service.url}",
        configuration = FeignConfig.class
)
public interface PacienteClient {

    // Llama al endpoint GET /api/v1/pacientes/{id} del microservicio de pacientes
    @GetMapping("/api/v1/pacientes/{id}")
    PacienteResponse obtenerPacientePorId(@PathVariable("id") Long id);
}