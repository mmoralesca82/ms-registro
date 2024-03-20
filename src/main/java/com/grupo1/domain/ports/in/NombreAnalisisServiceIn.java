package com.grupo1.domain.ports.in;

import com.grupo1.domain.aggregates.dto.EspecialidadMedicaDTO;
import com.grupo1.domain.aggregates.dto.NombreAnalisisDTO;
import com.grupo1.domain.aggregates.response.ResponseBase;

public interface NombreAnalisisServiceIn {

    ResponseBase findNombreAnalisisIn(String nombreAnalisis);

    ResponseBase findAllEnableNombreAnalisisIn();
    ResponseBase registerNombreAnalisisIn(NombreAnalisisDTO nombreAnalisisDTO, String username);

    ResponseBase  updateNombreAnalisisIn(NombreAnalisisDTO nombreAnalisisDTO, String username);

    ResponseBase  deleteNombreAnalisisIn(Long id, String username);

}
