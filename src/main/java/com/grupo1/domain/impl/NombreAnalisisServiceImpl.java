package com.grupo1.domain.impl;

import com.grupo1.domain.aggregates.dto.NombreAnalisisDTO;
import com.grupo1.domain.aggregates.response.ResponseBase;
import com.grupo1.domain.ports.in.NombreAnalisisServiceIn;
import com.grupo1.domain.ports.out.NombreAnalisisServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NombreAnalisisServiceImpl implements NombreAnalisisServiceIn {

    private final NombreAnalisisServiceOut nombreAnalisisServiceOut;

    @Override
    public ResponseBase findNombreAnalisisIn(String nombreAnalisis) {
        return nombreAnalisisServiceOut.findNombreAnalisisOut(nombreAnalisis);
    }

    @Override
    public ResponseBase findAllEnableNombreAnalisisIn() {
        return nombreAnalisisServiceOut.findAllEnableNombreAnalisisOut();
    }

    @Override
    public ResponseBase registerNombreAnalisisIn(NombreAnalisisDTO nombreAnalisisDTO, String username) {
        return nombreAnalisisServiceOut.registerNombreAnalisisOut(nombreAnalisisDTO, username);
    }

    @Override
    public ResponseBase updateNombreAnalisisIn(NombreAnalisisDTO nombreAnalisisDTO, String username) {
        return nombreAnalisisServiceOut.updateNombreAnalisisOut(nombreAnalisisDTO, username);
    }

    @Override
    public ResponseBase deleteNombreAnalisisIn(Long id, String username) {
        return nombreAnalisisServiceOut.deleteNombreAnalisisOut(id, username);
    }
}
