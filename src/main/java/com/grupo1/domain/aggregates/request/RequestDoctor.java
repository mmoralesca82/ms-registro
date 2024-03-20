package com.grupo1.domain.aggregates.request;


import com.grupo1.domain.aggregates.dto.DirecccionDTO;
import com.grupo1.domain.aggregates.dto.EspecialidadMedicaDTO;
import lombok.Data;

@Data
public class RequestDoctor {

    private String numDocumento;
    private String genero;
    private String registroMedico;
    private String telefono;
    private String especialidad;

    private DirecccionDTO direccion;




}
