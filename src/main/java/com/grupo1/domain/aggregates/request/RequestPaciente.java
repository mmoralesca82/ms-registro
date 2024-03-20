package com.grupo1.domain.aggregates.request;

import com.grupo1.domain.aggregates.dto.ContactoEmergenciaDTO;
import com.grupo1.domain.aggregates.dto.DirecccionDTO;
import lombok.Data;

import java.util.Set;


@Data
public class RequestPaciente {

    private String numDocumento;
    private String fechaNacimiento; //Formato dd-MM-yyyy
    private String genero;
    private String telefono;

    private DirecccionDTO direccion;
    private Set<ContactoEmergenciaDTO> contactos;

}
