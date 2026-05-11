package com.hospital_vm_cl.hospital_vm.services;

import com.hospital_vm_cl.hospital_vm.models.PacienteModel;
import com.hospital_vm_cl.hospital_vm.repositories.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteModel> obtenerTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<PacienteModel> obtenerPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Optional<PacienteModel> obtenerPorRut(String rut) {
        return pacienteRepository.findByRut(rut);
    }

    public PacienteModel guardar(PacienteModel paciente) {
        return pacienteRepository.save(paciente);
    }

    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }
}