package com.grupo1.domain.ports.out;


import com.grupo1.domain.aggregates.request.RequestDoctor;
import com.grupo1.domain.aggregates.response.ResponseBase;


public interface DoctorServiceOut {

    ResponseBase  buscarDoctorOut(String numDoc);

    ResponseBase buscarAllEnableDoctorOut();
    ResponseBase registerDoctorOut(RequestDoctor requestDoctor, String username);

    ResponseBase  updateDoctorOut(RequestDoctor requestDoctor, String username);

    ResponseBase  deleteDoctorOut(String numDoc, String username);


}
