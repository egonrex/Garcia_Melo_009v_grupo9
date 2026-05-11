package com.hospital_vm_cl.hospital_vm_atencion.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

// Esta clase representa la tabla atencion en la base de datos
@Data
@Entity
@Table(name = "atencion")
public class AtencionModel {

    // ID autoincremental de la atencion
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fecha de la atencion
    @Column(name = "fecha_atencion", nullable = false)
    private LocalDate fechaAtencion;

    // Hora de la atencion
    @Column(name = "hora_atencion", nullable = false)
    private LocalTime horaAtencion;

    // Costo monetario de la atencion
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costo;

    // ID del paciente en el microservicio de pacientes
    @Column(name = "id_paciente", nullable = false)
    private Long idPaciente;

    // Comentario opcional de la atencion
    @Column(length = 500)
    private String comentario;
}