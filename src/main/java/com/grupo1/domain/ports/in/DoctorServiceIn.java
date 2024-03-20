package com.grupo1.domain.ports.in;



import com.grupo1.domain.aggregates.request.RequestDoctor;
import com.grupo1.domain.aggregates.response.ResponseBase;



public interface DoctorServiceIn {

    ResponseBase  buscarDoctorIn(String numDoc);

    ResponseBase buscarAllEnableDoctorIn();
    ResponseBase registerDoctorIn(RequestDoctor requestDoctor, String username);

    ResponseBase  updateDoctorIn(RequestDoctor requestDoctor, String username);

    ResponseBase  deleteDoctorIn(String numDoc, String username);


}
