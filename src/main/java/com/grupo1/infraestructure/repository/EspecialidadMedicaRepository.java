package com.grupo1.infraestructure.repository;


import com.grupo1.infraestructure.entity.EspecialidadMedicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EspecialidadMedicaRepository extends JpaRepository<EspecialidadMedicaEntity,Long> {
    Optional<EspecialidadMedicaEntity> findByEspecialidad(String especialidad);

    List<EspecialidadMedicaEntity> findByEstado(Boolean estado);

}
