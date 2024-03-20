package com.grupo1.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="direccion")
@Getter
@Setter
public class DirecccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;
    @Column(nullable = false, length = 45)
    private String via;
    @Column(nullable = false)
    private Integer numeroPredio;
    @Column(nullable = true, length = 25)
    private String interior;
    @Column(nullable = true, length = 45)
    private String referencia;
    @Column(nullable = false, length = 25)
    private String distrito;
    @Column(nullable = false, length = 25)
    private String provincia;
    @Column(nullable = false, length = 25)
    private String departamento;


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
