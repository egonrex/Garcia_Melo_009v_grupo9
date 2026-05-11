package com.hospital_vm_cl.hospital_vm_atencion.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

// DTO de salida de la atencion
@Data
@Builder
public class AtencionResponse {

    private Long id;
    private LocalDate fechaAtencion;
    private LocalTime horaAtencion;
    private BigDecimal costo;
    private Long idPaciente;
    private String comentario;

    // Se agrega el paciente obtenido desde el otro microservicio
    private PacienteResponse paciente;
}