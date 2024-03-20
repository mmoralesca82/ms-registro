package com.grupo1.domain.ports.out;

import com.grupo1.domain.aggregates.dto.NombreAnalisisDTO;
import com.grupo1.domain.aggregates.response.ResponseBase;

public interface NombreAnalisisServiceOut {

    ResponseBase findNombreAnalisisOut(String nombreAnalisis);

    ResponseBase findAllEnableNombreAnalisisOut();
    ResponseBase registerNombreAnalisisOut(NombreAnalisisDTO nombreAnalisisDTO, String username);

    ResponseBase  updateNombreAnalisisOut(NombreAnalisisDTO nombreAnalisisDTO, String username);

    ResponseBase  deleteNombreAnalisisOut(Long id, String username);
}
