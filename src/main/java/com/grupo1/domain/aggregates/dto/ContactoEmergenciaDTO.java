package com.grupo1.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactoEmergenciaDTO {

    private Long idContactoEmerg;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;

}
