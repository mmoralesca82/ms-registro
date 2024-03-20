package com.grupo1.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="doctor")
@Getter
@Setter
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoctor;
    @Column(nullable = false, length = 40)
    private String nombre;
    @Column(nullable = false, length = 40)
    private String apellido;
    @Column(nullable = false, length = 15)
    private String numDocumento;
    @Column(nullable = false)
    private String genero;
    @Column(nullable = false, length = 15)
    private String registroMedico;
    @Column(nullable = false, length = 15)
    private String telefono;

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

//    @JsonIgnore
    @JsonIgnoreProperties("direccion")
    @ManyToOne(optional = false)
    @JoinColumn(name="id_direccion_fk", nullable = false)
    private DirecccionEntity direccion;

//    @JsonIgnore
    @JsonIgnoreProperties("especialidadMedica")
    @ManyToOne(optional = false)
    @JoinColumn(name="id_especialidad_fk", nullable = false)
    private EspecialidadMedicaEntity especialidadMedica;

}
