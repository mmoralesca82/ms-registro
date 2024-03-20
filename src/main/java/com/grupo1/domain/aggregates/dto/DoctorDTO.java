package com.grupo1.domain.aggregates.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDTO {

    private Long idDoctor;
    private String nombre;
    private String apellido;
    private String numDocumento;
    private String genero;
    private String registroMedico;
    private String telefono;


}
