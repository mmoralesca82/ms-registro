package com.grupo1.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor
public class EspecialidadMedicaDTO {

    private Long idEspecialidad;
    private String especialidad;
    private String complejidad;


}
