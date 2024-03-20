package com.grupo1.infraestructure.repository;

import com.grupo1.infraestructure.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    Optional<DoctorEntity> findByNumDocumento(String numDoc);

    Optional<DoctorEntity> findByRegistroMedico(String registroMedico);

    List<DoctorEntity> findByEstado(Boolean estado);




}
