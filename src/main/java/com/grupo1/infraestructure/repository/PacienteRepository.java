package com.grupo1.infraestructure.repository;

import com.grupo1.infraestructure.entity.ContactoEmergenciaEntity;
import com.grupo1.infraestructure.entity.DoctorEntity;
import com.grupo1.infraestructure.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    Optional<PacienteEntity> findByNumDocumento(String numDoc);

    List<PacienteEntity> findByEstado(Boolean estado);

}
