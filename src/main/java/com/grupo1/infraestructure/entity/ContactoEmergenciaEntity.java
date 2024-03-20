package com.grupo1.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="contacto_emergencia")
@Getter
@Setter
public class ContactoEmergenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContactoEmerg;
    @Column(nullable = false, length = 40)
    private String nombre;
    @Column(nullable = false, length = 20)
    private String apellidoPaterno;
    @Column(nullable = false, length = 20)
    private String apellidoMaterno;
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


//    @ManyToMany(mappedBy = "contactoEmergencia")
//    @JsonIgnoreProperties("pacientes")
//    private Set<PacienteEntity> pacientes;


}
