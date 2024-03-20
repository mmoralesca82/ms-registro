package com.grupo1.infraestructure.repository;


import com.grupo1.infraestructure.entity.NombreAnalisisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface NombreAnalisisRepository extends JpaRepository<NombreAnalisisEntity, Long> {

    Optional<NombreAnalisisEntity> findByAnalisis(String nombreAnalisis);

    List<NombreAnalisisEntity> findByEstado(Boolean estado);

}
