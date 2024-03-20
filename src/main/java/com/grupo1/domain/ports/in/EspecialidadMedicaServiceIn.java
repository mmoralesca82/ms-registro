package com.grupo1.domain.ports.in;

import com.grupo1.domain.aggregates.dto.EspecialidadMedicaDTO;
import com.grupo1.domain.aggregates.response.ResponseBase;

public interface EspecialidadMedicaServiceIn {

    ResponseBase findEspecialidadMedicaIn(String especialidad);

    ResponseBase findAllEnableEspecialidadMedicaIn();
    ResponseBase registerEspecialidadMedicaIn(EspecialidadMedicaDTO especialidadMedicaDTO, String username);

    ResponseBase  updateEspecialidadMedicaIn(EspecialidadMedicaDTO especialidadMedicaDTO, String username);

    ResponseBase  deleteEspecialidadMedicaIn(Long id, String username);

}
