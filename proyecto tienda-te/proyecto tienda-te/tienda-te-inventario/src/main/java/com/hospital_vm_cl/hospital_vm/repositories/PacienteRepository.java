package com.hospital_vm_cl.hospital_vm.repositories;

import com.hospital_vm_cl.hospital_vm.models.PacienteModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {

    Optional<PacienteModel> findByRut(String rut);
    boolean existsByRut(String rut);
}