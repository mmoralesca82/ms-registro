package com.grupo1.domain.ports.out;


import com.grupo1.domain.aggregates.request.RequestPaciente;
import com.grupo1.domain.aggregates.response.ResponseBase;


public interface PacienteServiceOut {

    ResponseBase buscarDoctorOut(String numDoc);

    ResponseBase buscarAllEnableDoctorOut();

    ResponseBase  registerPacienteOut(RequestPaciente requestPaciente, String username);

    ResponseBase  updatePacienteOut(RequestPaciente requestPaciente, String username);

    ResponseBase  deletePacienteOut (String numDoc, String username);
}
