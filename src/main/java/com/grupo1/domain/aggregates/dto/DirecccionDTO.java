package com.grupo1.domain.aggregates.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirecccionDTO {

    private Long idDireccion;
    private String via;
    private Integer numeroPredio;
    private String interior;
    private String referencia;
    private String distrito;
    private String provincia;
    private String departamento;


}
