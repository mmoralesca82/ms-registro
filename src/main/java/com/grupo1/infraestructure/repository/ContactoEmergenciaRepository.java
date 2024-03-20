package com.grupo1.infraestructure.repository;

import com.grupo1.infraestructure.entity.ContactoEmergenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactoEmergenciaRepository extends JpaRepository<ContactoEmergenciaEntity, Long> {

    Optional<ContactoEmergenciaEntity> findByTelefono (String telefono);

}
