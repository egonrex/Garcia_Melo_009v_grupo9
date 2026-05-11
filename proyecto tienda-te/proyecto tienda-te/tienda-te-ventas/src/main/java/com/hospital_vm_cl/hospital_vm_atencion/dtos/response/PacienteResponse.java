package com.hospital_vm_cl.hospital_vm_atencion.dtos.response;

import lombok.Data;

import java.time.LocalDate;

// DTO que representa la respuesta del microservicio de pacientes
@Data
public class PacienteResponse {

    private Long id;
    private String rut;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String correo;
}