package com.grupo1.domain.impl;

import com.grupo1.domain.aggregates.dto.EspecialidadMedicaDTO;
import com.grupo1.domain.aggregates.response.ResponseBase;
import com.grupo1.domain.ports.in.EspecialidadMedicaServiceIn;
import com.grupo1.domain.ports.out.EspecialidadMedicaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EspecialidadMedicaServiceImpl implements EspecialidadMedicaServiceIn {

    private  final EspecialidadMedicaServiceOut especialidadMedicaServiceOut;

    @Override
    public ResponseBase findEspecialidadMedicaIn(String especialidad) {
        return especialidadMedicaServiceOut.findEspecialidadMedicaOut(especialidad);
    }

    @Override
    public ResponseBase findAllEnableEspecialidadMedicaIn() {
        return especialidadMedicaServiceOut.findAllEnableEspecialidadMedicaOut();
    }

    @Override
    public ResponseBase registerEspecialidadMedicaIn(EspecialidadMedicaDTO especialidadMedicaDTO, String username) {
        return especialidadMedicaServiceOut.registerEspecialidadMedicaOut(especialidadMedicaDTO,username);
    }

    @Override
    public ResponseBase updateEspecialidadMedicaIn(EspecialidadMedicaDTO especialidadMedicaDTO, String username) {
        return especialidadMedicaServiceOut.updateEspecialidadMedicaOut(especialidadMedicaDTO,username);
    }

    @Override
    public ResponseBase deleteEspecialidadMedicaIn(Long id, String username) {
        return especialidadMedicaServiceOut.deleteEspecialidadMedicaOut(id,username);
    }
}
