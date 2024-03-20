package com.grupo1.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="especialidad_medica")
@Getter
@Setter
public class EspecialidadMedicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecialidad;
    @Column(nullable = false, length = 50)
    private String especialidad;
    @Column(nullable = false, length = 5)
    private String complejidad; // Complejidad: MED1, MED2 y MED3


    @Column(nullable = false, length = 25)
    private String usuarioCreacion;
    @Column(nullable = false)
    private Timestamp fechaCreacion;
    @Column(length = 25)
    private String usuarioModificacion;
    private Timestamp fechaModificacion;
    @Column(length = 25)
    private String usuarioEliminacion;
    private Timestamp fechaEliminacion;
    @Column(nullable = false)
    private Boolean estado;

}
