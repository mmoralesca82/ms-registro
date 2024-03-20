package com.grupo1.domain.ports.out;

import com.grupo1.domain.aggregates.dto.EspecialidadMedicaDTO;
import com.grupo1.domain.aggregates.response.ResponseBase;

public interface EspecialidadMedicaServiceOut {

    ResponseBase findEspecialidadMedicaOut(String especialidad);

    ResponseBase findAllEnableEspecialidadMedicaOut();
    ResponseBase registerEspecialidadMedicaOut(EspecialidadMedicaDTO especialidadMedicaDTO, String username);

    ResponseBase  updateEspecialidadMedicaOut(EspecialidadMedicaDTO especialidadMedicaDTO, String username);

    ResponseBase  deleteEspecialidadMedicaOut(Long id, String username);

}
