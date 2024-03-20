package com.grupo1.infraestructure.adapter;

import com.grupo1.domain.aggregates.constants.Constants;
import com.grupo1.domain.aggregates.dto.DoctorDTO;
import com.grupo1.domain.aggregates.request.RequestDoctor;
import com.grupo1.domain.aggregates.response.MsExternalToReniecResponse;
import com.grupo1.domain.aggregates.response.ResponseBase;
import com.grupo1.domain.ports.out.DoctorServiceOut;
import com.grupo1.infraestructure.entity.DirecccionEntity;
import com.grupo1.infraestructure.entity.DoctorEntity;
import com.grupo1.infraestructure.entity.EspecialidadMedicaEntity;
import com.grupo1.infraestructure.mapper.GenericMapper;
import com.grupo1.infraestructure.repository.DirecccionRepository;
import com.grupo1.infraestructure.repository.DoctorRepository;
import com.grupo1.infraestructure.repository.EspecialidadMedicaRepository;
import com.grupo1.infraestructure.rest.client.ToMSExternalApi;
import com.grupo1.infraestructure.util.CurrentTime;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DoctorAdapter implements DoctorServiceOut {

    private final DoctorRepository doctorRepository;
    private final DirecccionRepository direcccionRepository;
    private final EspecialidadMedicaRepository especialidadMedicaRepository;
    private final ToMSExternalApi toMSExternalApi;
    private final GenericMapper genericMapper;


    @Override
    public ResponseBase buscarDoctorOut(String numDoc) {
        Optional<DoctorEntity> getDoctor = doctorRepository.findByNumDocumento(numDoc);
        if(getDoctor.isPresent()){
            return  new ResponseBase(302, "Informacion encotrada con exito", getDoctor);
        }

        return new ResponseBase(404, "No se encontro la informacion", null);
    }




    @Override
    public ResponseBase buscarAllEnableDoctorOut() {
        List<DoctorDTO> doctorDTOList = new ArrayList<>();
        List<DoctorEntity> doctorEntities = doctorRepository.findByEstado(true);
        for(DoctorEntity doctor : doctorEntities){
           DoctorDTO doctorDTO = genericMapper.mapDoctorEntityToDoctorDTO(doctor);
           doctorDTOList.add(doctorDTO);
        }
        return  new ResponseBase(302, "Informacion encotrada con exito", doctorDTOList);
    }

    @Override
    public ResponseBase registerDoctorOut(RequestDoctor requestDoctor, String username) {
        ///////////////////Validar campos obligatorios ///////////////////////////////////////////
        if(requestDoctor.getNumDocumento()==null || requestDoctor.getNumDocumento().isEmpty() ||
                requestDoctor.getGenero()==null|| requestDoctor.getGenero().isEmpty() ||
                requestDoctor.getRegistroMedico()==null || requestDoctor.getRegistroMedico().isEmpty() ||
                requestDoctor.getTelefono()==null || requestDoctor.getTelefono().isEmpty() ||
                requestDoctor.getEspecialidad()==null || requestDoctor.getEspecialidad().isEmpty() ||
                requestDoctor.getDireccion().getVia()==null|| requestDoctor.getDireccion().getVia().isEmpty() ||
                requestDoctor.getDireccion().getNumeroPredio()==null|| requestDoctor.getDireccion().getNumeroPredio().toString().isEmpty() ||
                requestDoctor.getDireccion().getDistrito()==null|| requestDoctor.getDireccion().getDistrito().isEmpty() ||
                requestDoctor.getDireccion().getProvincia()==null|| requestDoctor.getDireccion().getProvincia().isEmpty() ||
                requestDoctor.getDireccion().getDepartamento()==null || requestDoctor.getDireccion().getDepartamento().isEmpty()) {
            return  new ResponseBase(406, "Datos obligatorios incompletos.", null);
        }
        ////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////Validar si la especialidad existe en base de datos //////////////
        Optional<EspecialidadMedicaEntity> getEspecialidad = especialidadMedicaRepository.findByEspecialidad(requestDoctor.getEspecialidad());
        if(getEspecialidad.isEmpty()){
            return  new ResponseBase(406, "La especialidad "+requestDoctor.getEspecialidad()+
                    " no existe.", null);
        }
        ////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////Validar si el doctor existe en base de datos ////////////////////
        Optional<DoctorEntity> getDoctor = doctorRepository.findByNumDocumento(requestDoctor.getNumDocumento());
        if(getDoctor.isPresent()){
            return  new ResponseBase(406, "Ya existe un registro doctor con el numero de documento "+
                    requestDoctor.getNumDocumento(), null);
        }
        ////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////Validar si el registro medico existe en base de datos ///////////
        Optional<DoctorEntity> getDoctorByRegistro = doctorRepository.findByRegistroMedico(requestDoctor.getRegistroMedico());
        if(getDoctorByRegistro.isPresent()){
            return  new ResponseBase(406, "Ya existe un medico con el numero de registro medico."+
                    requestDoctor.getRegistroMedico(), null);
        }
        ////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////Buscar DNI en Reniec///////////////////// ///////////////////////
        MsExternalToReniecResponse getInfoReniec = toMSExternalApi.getInfoExtReniec(requestDoctor.getNumDocumento());
        if(getInfoReniec.getApellidoMaterno()==null || getInfoReniec.getApellidoMaterno().isEmpty()){
            return  new ResponseBase(406, "El documento "+requestDoctor.getNumDocumento()+
                    " no existe en Reniec.", null);
        }
        ////////////////////////////////////////////////////////////////////////////////////////
        DirecccionEntity direccion = genericMapper.mapDireccionDtoToDireccionEntity(requestDoctor.getDireccion());
        direccion.setUsuarioCreacion(username);
        direccion.setFechaCreacion(CurrentTime.getTimestamp());
        direccion.setEstado(Constants.STATUS_ACTIVE);
        direccion = direcccionRepository.save(direccion);

        DoctorEntity doctor = new DoctorEntity();
        doctor = genericMapper.mapRequestDoctorToDoctorEntity(requestDoctor);
//        doctor.setNumDocumento(requestDoctor.getNumDocumento());
//        doctor.setGenero(requestDoctor.getGenero());
//        doctor.setRegistroMedico(requestDoctor.getRegistroMedico());
//        doctor.setTelefono(requestDoctor.getTelefono());
        doctor.setNombre(getInfoReniec.getNombres());
        doctor.setApellido(getInfoReniec.getApellidoPaterno()+" "+getInfoReniec.getApellidoMaterno());
        doctor.setUsuarioCreacion(username);
        doctor.setFechaCreacion(CurrentTime.getTimestamp());
        doctor.setEstado(Constants.STATUS_ACTIVE);
        doctor.setDireccion(direccion);
        doctor.setEspecialidadMedica(getEspecialidad.get());
        doctor = doctorRepository.save(doctor);

        return new ResponseBase(201, "Registrado con exito.", doctor );
    }

    @Override
    public ResponseBase updateDoctorOut(RequestDoctor requestDoctor, String username) {
        if(requestDoctor.getNumDocumento()==null || requestDoctor.getNumDocumento().isEmpty())
            return  new ResponseBase(406, "Se requiere numero de documento.", null);
        Optional<DoctorEntity> getDoctor = doctorRepository.findByNumDocumento(requestDoctor.getNumDocumento());
        if(getDoctor.isEmpty()){
            return new ResponseBase(404, "No se encontro el registro.", null);
        }
        if(!(requestDoctor.getRegistroMedico()==null || requestDoctor.getRegistroMedico().isEmpty())) {
            Optional<DoctorEntity> getDoctorByRegMedico = doctorRepository.findByRegistroMedico(requestDoctor.getRegistroMedico());
            if(getDoctorByRegMedico.isPresent() && !Objects.equals(getDoctorByRegMedico, getDoctor)) {
                return  new ResponseBase(406, "Ya existe un medico con el numero de registro medico."+
                        requestDoctor.getRegistroMedico(), null);
            }
            getDoctor.get().setRegistroMedico(requestDoctor.getRegistroMedico());
        }
        if(!(requestDoctor.getGenero()==null||
                requestDoctor.getGenero().isEmpty())) getDoctor.get().setGenero(requestDoctor.getGenero());
        if(!(requestDoctor.getTelefono()==null || requestDoctor.getTelefono().isEmpty()))
            getDoctor.get().setTelefono(requestDoctor.getTelefono());
        if(!(requestDoctor.getEspecialidad()==null || requestDoctor.getEspecialidad().isEmpty())){
            Optional<EspecialidadMedicaEntity> getEspecialidad = especialidadMedicaRepository.findByEspecialidad(requestDoctor.getEspecialidad());
            getEspecialidad.ifPresent(especialidadMedicaEntity -> getDoctor.get().setEspecialidadMedica(especialidadMedicaEntity));
        }
       if(!(requestDoctor.getDireccion().getVia()==null || requestDoctor.getDireccion().getVia().isEmpty()))
           getDoctor.get().getDireccion().setVia(requestDoctor.getDireccion().getVia());
        if(!(requestDoctor.getDireccion().getNumeroPredio()==null || requestDoctor.getDireccion().getNumeroPredio().toString().isEmpty()))
            getDoctor.get().getDireccion().setNumeroPredio(requestDoctor.getDireccion().getNumeroPredio());
        if(!(requestDoctor.getDireccion().getInterior()==null || requestDoctor.getDireccion().getInterior().isEmpty()))
            getDoctor.get().getDireccion().setInterior(requestDoctor.getDireccion().getInterior());
        if(!(requestDoctor.getDireccion().getReferencia()==null || requestDoctor.getDireccion().getReferencia().isEmpty()))
            getDoctor.get().getDireccion().setReferencia(requestDoctor.getDireccion().getReferencia());
        if(!(requestDoctor.getDireccion().getDistrito()==null || requestDoctor.getDireccion().getDistrito().isEmpty()))
            getDoctor.get().getDireccion().setDistrito(requestDoctor.getDireccion().getDistrito());
        if(!(requestDoctor.getDireccion().getProvincia()==null || requestDoctor.getDireccion().getProvincia().isEmpty()))
            getDoctor.get().getDireccion().setProvincia(requestDoctor.getDireccion().getProvincia());
        if(!(requestDoctor.getDireccion().getDepartamento()==null || requestDoctor.getDireccion().getDepartamento().isEmpty()))
            getDoctor.get().getDireccion().setDepartamento(requestDoctor.getDireccion().getDepartamento());
        return new ResponseBase(200, "Registro actualizado",doctorRepository.save(getDoctor.get()));
    }

    @Override
    public ResponseBase deleteDoctorOut(String numDoc, String username) {
        Optional<DoctorEntity> findDoctor = doctorRepository.findByNumDocumento(numDoc);
        if(findDoctor.isPresent()){
            findDoctor.get().setEstado(Constants.STATUS_INACTIVE);
            findDoctor.get().setUsuarioEliminacion(username);
            findDoctor.get().setFechaEliminacion(CurrentTime.getTimestamp());

            return new ResponseBase(202, "Registro eliminado",
                    doctorRepository.save(findDoctor.get()));
        }
        return new ResponseBase(404, "No se encontro al doctor.", null);
    }

}
