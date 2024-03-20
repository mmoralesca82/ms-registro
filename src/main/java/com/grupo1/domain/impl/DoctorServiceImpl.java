package com.grupo1.domain.impl;

import com.grupo1.domain.aggregates.request.RequestDoctor;
import com.grupo1.domain.aggregates.response.ResponseBase;
import com.grupo1.domain.ports.in.DoctorServiceIn;
import com.grupo1.domain.ports.out.DoctorServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorServiceIn {

    private final DoctorServiceOut doctorServiceOut;

    @Override
    public ResponseBase buscarDoctorIn(String numDoc) {
        return doctorServiceOut.buscarDoctorOut(numDoc);
    }

    @Override
    public ResponseBase buscarAllEnableDoctorIn() {
        return doctorServiceOut.buscarAllEnableDoctorOut();
    }

    @Override
    public ResponseBase registerDoctorIn(RequestDoctor requestDoctor, String username) {
        return doctorServiceOut.registerDoctorOut(requestDoctor, username);
    }

    @Override
    public ResponseBase  updateDoctorIn(RequestDoctor requestDoctor, String username) {
        return doctorServiceOut.updateDoctorOut(requestDoctor, username);
    }

    @Override
    public ResponseBase deleteDoctorIn(String numDoc, String username) {
        return doctorServiceOut.deleteDoctorOut(numDoc, username);
    }


}
