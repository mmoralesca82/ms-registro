package com.grupo1.domain.ports.in;


import com.grupo1.domain.aggregates.request.RequestPaciente;
import com.grupo1.domain.aggregates.response.ResponseBase;
import org.apache.coyote.Response;


public interface PacienteServiceIn {

    ResponseBase buscarDoctorIn(String numDoc);

    ResponseBase buscarAllEnableDoctorIn();

    ResponseBase registerPacienteIn(RequestPaciente requestPaciente, String username);

    ResponseBase  updatePacienteIn(RequestPaciente requestPaciente, String username);

    ResponseBase deletePacienteIn(String numDoc, String username);
}
