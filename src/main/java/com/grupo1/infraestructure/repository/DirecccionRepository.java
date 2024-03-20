package com.grupo1.infraestructure.repository;

import com.grupo1.infraestructure.entity.DirecccionEntity;
import com.grupo1.infraestructure.entity.DoctorEntity;
import jdk.jshell.execution.DirectExecutionControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirecccionRepository extends JpaRepository<DirecccionEntity,Long> {


}
