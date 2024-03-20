package com.grupo1.infraestructure.mapper;


import com.grupo1.domain.aggregates.dto.*;
import com.grupo1.domain.aggregates.request.RequestDoctor;
import com.grupo1.domain.aggregates.request.RequestPaciente;
import com.grupo1.infraestructure.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GenericMapper {
    private static final ModelMapper modelMapper = new ModelMapper();


    public DoctorDTO mapDoctorEntityToDoctorDTO(DoctorEntity doctorEntity){
        return modelMapper.map(doctorEntity, DoctorDTO.class);
    }

    public DirecccionEntity mapDireccionDtoToDireccionEntity(DirecccionDTO direcccionDTO){
        return modelMapper.map(direcccionDTO, DirecccionEntity.class);
    }

    public EspecialidadMedicaDTO mapEspecialidadEntityToEspecialidadDTO(EspecialidadMedicaEntity especilidad){
        return modelMapper.map(especilidad, EspecialidadMedicaDTO.class);
    }

    public NombreAnalisisDTO mapNombreAnalisisEntityToNombreAnalisisDTO(NombreAnalisisEntity analisis){
        return modelMapper.map(analisis, NombreAnalisisDTO.class);
    }

    public PacienteDTO mapPacienteEntityToPacienteDTO(PacienteEntity paciente){
        return modelMapper.map(paciente, PacienteDTO.class);
    }

    public DoctorEntity mapRequestDoctorToDoctorEntity(RequestDoctor requestDoctor){
        return modelMapper.map(requestDoctor, DoctorEntity.class);
    }

    public ContactoEmergenciaDTO mapContactoEntityToContactoDTO(ContactoEmergenciaEntity contacto){
        return modelMapper.map(contacto, ContactoEmergenciaDTO.class);
    }

    public PacienteEntity mapRequestPacienteToPacienteEntity(RequestPaciente requestPaciente){
        return modelMapper.map(requestPaciente, PacienteEntity.class);
    }

    public ContactoEmergenciaEntity mapContactoDtoToContactoEntity (ContactoEmergenciaDTO contacto){
        return modelMapper.map(contacto, ContactoEmergenciaEntity.class);
    }

}
