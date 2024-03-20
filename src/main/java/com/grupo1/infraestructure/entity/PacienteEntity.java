package com.grupo1.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name="paciente")
@Getter
@Setter
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;
    @Column(nullable = false, length = 40)
    private String nombre;
    @Column(nullable = false, length = 40)
    private String apellido;
    @Column(nullable = false, length = 15)
    private String numDocumento;
    @Column(nullable = false)
    private Date fechaNacimiento;
    @Column(nullable = false)
    private String genero;
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


    @JsonIgnoreProperties("direccion")
    @ManyToOne(optional = false)
    @JoinColumn(name="id_direccion_fk", nullable = false)
    private DirecccionEntity direccion;

    @JsonIgnoreProperties("contactoEmergencia")
    @ManyToMany
    @JoinTable(name="paciente_contacto",
            joinColumns = @JoinColumn(name="id_paciente_fk", nullable = false),
            inverseJoinColumns = @JoinColumn(name="id_contacto_emerg_fk", nullable = false))
    private Set<ContactoEmergenciaEntity> contactoEmergencia;



}
