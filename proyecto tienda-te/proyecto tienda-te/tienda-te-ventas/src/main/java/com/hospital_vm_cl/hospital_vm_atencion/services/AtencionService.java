package com.hospital_vm_cl.hospital_vm_atencion.services;

import com.hospital_vm_cl.hospital_vm_atencion.client.PacienteClient;
import com.hospital_vm_cl.hospital_vm_atencion.dtos.request.AtencionRequest;
import com.hospital_vm_cl.hospital_vm_atencion.dtos.response.AtencionResponse;
import com.hospital_vm_cl.hospital_vm_atencion.dtos.response.PacienteResponse;
import com.hospital_vm_cl.hospital_vm_atencion.exceptions.NotFoundException;
import com.hospital_vm_cl.hospital_vm_atencion.exceptions.RemoteServiceException;
import com.hospital_vm_cl.hospital_vm_atencion.models.AtencionModel;
import com.hospital_vm_cl.hospital_vm_atencion.repositories.AtencionRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Contiene la lógica del negocio relacionada con las atenciones
@Service
@Transactional
public class AtencionService {

    private final AtencionRepository atencionRepository;
    private final PacienteClient pacienteClient;

    public AtencionService(AtencionRepository atencionRepository, PacienteClient pacienteClient) {
        this.atencionRepository = atencionRepository;
        this.pacienteClient = pacienteClient;
    }

    // Lista todas las atenciones
    public List<AtencionResponse> obtenerTodas() {
        return atencionRepository.findAll()
                .stream()
                .map(this::mapToResponseConPaciente)
                .toList();
    }

    // Busca una atencion por id
    public AtencionResponse obtenerPorId(Long id) {
        AtencionModel atencion = atencionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe la atención con id: " + id));

        return mapToResponseConPaciente(atencion);
    }

    // Lista todas las atenciones de un paciente
    public List<AtencionResponse> obtenerPorPaciente(Long idPaciente) {
        return atencionRepository.findByIdPaciente(idPaciente)
                .stream()
                .map(this::mapToResponseConPaciente)
                .toList();
    }

    // Guarda una nueva atencion
    public AtencionResponse guardar(AtencionRequest request) {
        // Antes de guardar, verificamos que el paciente exista en el otro microservicio
        PacienteResponse paciente = obtenerPacienteDesdeServicio(request.getIdPaciente());

        AtencionModel atencion = new AtencionModel();
        atencion.setFechaAtencion(request.getFechaAtencion());
        atencion.setHoraAtencion(request.getHoraAtencion());
        atencion.setCosto(request.getCosto());
        atencion.setIdPaciente(request.getIdPaciente());
        atencion.setComentario(request.getComentario());

        AtencionModel guardada = atencionRepository.save(atencion);

        return mapToResponse(guardada, paciente);
    }

    // Actualiza una atencion existente
    public AtencionResponse actualizar(Long id, AtencionRequest request) {
        AtencionModel atencion = atencionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe la atención con id: " + id));

        // Verificamos que el paciente siga existiendo
        PacienteResponse paciente = obtenerPacienteDesdeServicio(request.getIdPaciente());

        atencion.setFechaAtencion(request.getFechaAtencion());
        atencion.setHoraAtencion(request.getHoraAtencion());
        atencion.setCosto(request.getCosto());
        atencion.setIdPaciente(request.getIdPaciente());
        atencion.setComentario(request.getComentario());

        AtencionModel actualizada = atencionRepository.save(atencion);

        return mapToResponse(actualizada, paciente);
    }

    // Elimina una atencion por id
    public void eliminar(Long id) {
        AtencionModel atencion = atencionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe la atención con id: " + id));

        atencionRepository.delete(atencion);
    }

    // Obtiene el paciente usando Feign
    private PacienteResponse obtenerPacienteDesdeServicio(Long idPaciente) {
        try {
            return pacienteClient.obtenerPacientePorId(idPaciente);
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("No existe el paciente con id: " + idPaciente);
        } catch (FeignException e) {
            throw new RemoteServiceException("Error al comunicarse con el microservicio de pacientes");
        }
    }

    // Convierte la entidad a DTO consultando primero el paciente
    private AtencionResponse mapToResponseConPaciente(AtencionModel atencion) {
        PacienteResponse paciente = obtenerPacienteDesdeServicio(atencion.getIdPaciente());
        return mapToResponse(atencion, paciente);
    }

    // Convierte entidad + paciente en DTO de salida
    private AtencionResponse mapToResponse(AtencionModel atencion, PacienteResponse paciente) {
        return AtencionResponse.builder()
                .id(atencion.getId())
                .fechaAtencion(atencion.getFechaAtencion())
                .horaAtencion(atencion.getHoraAtencion())
                .costo(atencion.getCosto())
                .idPaciente(atencion.getIdPaciente())
                .comentario(atencion.getComentario())
                .paciente(paciente)
                .build();
    }
}