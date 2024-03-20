package com.grupo1.domain.impl;

import com.grupo1.domain.aggregates.dto.PacienteDTO;
import com.grupo1.domain.aggregates.request.RequestPaciente;
import com.grupo1.domain.aggregates.response.ResponseBase;
import com.grupo1.domain.ports.in.PacienteServiceIn;
import com.grupo1.domain.ports.out.PacienteServiceOut;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteServiceIn {

    private final PacienteServiceOut pacienteServiceOut;

    @Override
    public ResponseBase buscarDoctorIn(String numDoc) {
        return pacienteServiceOut.buscarDoctorOut(numDoc);
    }

    @Override
    public ResponseBase buscarAllEnableDoctorIn() {
        return pacienteServiceOut.buscarAllEnableDoctorOut();
    }

    @Override
    public ResponseBase registerPacienteIn(RequestPaciente requestPaciente, String username) {
        return pacienteServiceOut.registerPacienteOut(requestPaciente,username);
    }

    @Override
    public ResponseBase  updatePacienteIn(RequestPaciente requestPaciente, String username) {
        return pacienteServiceOut.updatePacienteOut(requestPaciente, username);
    }

    @Override
    public ResponseBase deletePacienteIn(String numDoc, String username) {
        return pacienteServiceOut.deletePacienteOut(numDoc, username);
    }
}
