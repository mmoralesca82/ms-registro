package com.grupo1.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;



@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PacienteDTO {


    private Long idPaciente;
    private String nombre;
    private String apellido;
    private String numDocumento;
    private Date fechaNacimiento;
    private String genero;
    private String telefono;


}
