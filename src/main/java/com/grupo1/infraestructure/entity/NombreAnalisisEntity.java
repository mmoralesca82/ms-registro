package com.grupo1.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name="nombre_analisis")
@Getter
@Setter
public class NombreAnalisisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNombreAnalisis;
    @Column(nullable = false, length = 50)
    private String analisis;
    @Column(nullable = false, length = 5)
    private String complejidad; // Complejidad: LAB1, LAB2 y LAB3


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
