package com.hospital_vm_cl.hospital_vm_atencion.repositories;


import com.hospital_vm_cl.hospital_vm_atencion.models.AtencionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repositorio para acceder a la tabla atencion
@Repository
public interface AtencionRepository extends JpaRepository<AtencionModel, Long> {

    // Permite listar las atenciones de un paciente específico
    List<AtencionModel> findByIdPaciente(Long idPaciente);
}
