package com.hospital_vm_cl.hospital_vm_atencion.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

// DTO de entrada para crear o actualizar una atencion
@Data
public class AtencionRequest {

    @NotNull(message = "La fecha de atención es obligatoria")
    private LocalDate fechaAtencion;

    @NotNull(message = "La hora de atención es obligatoria")
    private LocalTime horaAtencion;

    @NotNull(message = "El costo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El costo debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El costo debe tener hasta 8 enteros y 2 decimales")
    private BigDecimal costo;

    @NotNull(message = "El id del paciente es obligatorio")
    @Positive(message = "El id del paciente debe ser mayor a 0")
    private Long idPaciente;

    @Size(max = 500, message = "El comentario no puede superar los 500 caracteres")
    private String comentario;
}