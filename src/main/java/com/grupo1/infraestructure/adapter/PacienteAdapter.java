package com.grupo1.infraestructure.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo1.domain.aggregates.constants.Constants;
import com.grupo1.domain.aggregates.dto.ContactoEmergenciaDTO;
import com.grupo1.domain.aggregates.dto.PacienteDTO;
import com.grupo1.domain.aggregates.request.RequestPaciente;
import com.grupo1.domain.aggregates.response.MsExternalToReniecResponse;
import com.grupo1.domain.aggregates.response.ResponseBase;
import com.grupo1.domain.ports.out.PacienteServiceOut;
import com.grupo1.infraestructure.entity.*;
import com.grupo1.infraestructure.mapper.GenericMapper;
import com.grupo1.infraestructure.repository.ContactoEmergenciaRepository;
import com.grupo1.infraestructure.repository.DirecccionRepository;
import com.grupo1.infraestructure.repository.PacienteRepository;
import com.grupo1.infraestructure.rest.client.ToMSExternalApi;
import com.grupo1.infraestructure.util.CurrentTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PacienteAdapter implements PacienteServiceOut {

    private final PacienteRepository pacienteRepository;
    private final DirecccionRepository direcccionRepository;
    private final ContactoEmergenciaRepository contactoEmergenciaRepository;
    private final GenericMapper genericMapper;
    private final ToMSExternalApi toMSExternalApi;


    @Override
    public ResponseBase buscarDoctorOut(String numDoc) {
        Optional<PacienteEntity> getPaciente = pacienteRepository.findByNumDocumento(numDoc);
        if (getPaciente.isPresent()) {
            return new ResponseBase(302, "Informacion encotrada con exito", getPaciente);
        }

        return new ResponseBase(404, "No se encontro la informacion", null);
    }

    @Override
    public ResponseBase buscarAllEnableDoctorOut() {
        List<PacienteDTO> pacienteDTOList = new ArrayList<>();
        List<PacienteEntity> pacienteEntities = pacienteRepository.findByEstado(true);
        for (PacienteEntity paciente : pacienteEntities) {
            PacienteDTO pacienteDTO = genericMapper.mapPacienteEntityToPacienteDTO(paciente);
            pacienteDTOList.add(pacienteDTO);
        }
        return new ResponseBase(302, "Informacion encotrada con exito", pacienteDTOList);
    }

    @Override
    public ResponseBase registerPacienteOut(RequestPaciente requestPaciente, String username) {
        ///////////////////Validar campos obligatorios ///////////////////////////////////////////
        if (requestPaciente.getNumDocumento() == null || requestPaciente.getNumDocumento().isEmpty() ||
                requestPaciente.getFechaNacimiento() == null || requestPaciente.getFechaNacimiento().isEmpty() ||
                requestPaciente.getGenero() == null || requestPaciente.getGenero().isEmpty() ||
                requestPaciente.getTelefono() == null || requestPaciente.getTelefono().isEmpty() ||
                requestPaciente.getDireccion().getVia() == null || requestPaciente.getDireccion().getVia().isEmpty() ||
                requestPaciente.getDireccion().getNumeroPredio() == null || requestPaciente.getDireccion().getNumeroPredio().toString().isEmpty() ||
                requestPaciente.getDireccion().getDistrito() == null || requestPaciente.getDireccion().getDistrito().isEmpty() ||
                requestPaciente.getDireccion().getProvincia() == null || requestPaciente.getDireccion().getProvincia().isEmpty() ||
                requestPaciente.getDireccion().getDepartamento() == null || requestPaciente.getDireccion().getDepartamento().isEmpty()) {
            return new ResponseBase(406, "Datos obligatorios incompletos.", null);
        }
        ////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////Validar si el paciente existe en base de datos ////////////////////
        Optional<PacienteEntity> getPaciente = pacienteRepository.findByNumDocumento(requestPaciente.getNumDocumento());
        if (getPaciente.isPresent()) {
            return new ResponseBase(406, "Ya existe un registro paciente con el numero de documento " +
                    requestPaciente.getNumDocumento(), null);
        }
        ////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////Validación de contactos////////////////////////////////////
        Set<ContactoEmergenciaEntity> listaContactosEntities = new HashSet<>();
        StringBuilder mensajeContactos = new StringBuilder();
        if (requestPaciente.getContactos() != null && !requestPaciente.getContactos().isEmpty()) {
            Set<ContactoEmergenciaDTO> listaContactosDto = requestPaciente.getContactos();
            for (ContactoEmergenciaDTO contacto : listaContactosDto) {
                if (contacto.getNombre() == null || contacto.getNombre().isEmpty() ||
                        contacto.getApellidoPaterno() == null || contacto.getApellidoPaterno().isEmpty() ||
                        contacto.getApellidoMaterno() == null || contacto.getApellidoMaterno().isEmpty() ||
                        contacto.getTelefono() == null || contacto.getTelefono().isEmpty()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        mensajeContactos.append(" /// Contacto: ").append(objectMapper.writeValueAsString(contacto)).
                                append(" no registrado a paciente por datos obligatorios incompletos.");
                    }catch  (Exception e){
                        System.out.println(e);
                    }
                } else {
                    Optional<ContactoEmergenciaEntity> getContacto = contactoEmergenciaRepository.
                            findByTelefono(contacto.getTelefono());
                    if (getContacto.isPresent()) {
                        if (contacto.getNombre().equals(getContacto.get().getNombre()) &&
                                contacto.getApellidoPaterno().equals(getContacto.get().getApellidoPaterno()) &&
                                contacto.getApellidoMaterno().equals(getContacto.get().getApellidoMaterno())) {
                            listaContactosEntities.add(getContacto.get());
                        } else {
                            mensajeContactos.append(" /// Contacto con telefono ").append(contacto.getTelefono()).
                                    append(" existe en base de datos y no se asignó a paciente porque no coinciden con datos ingresados.");
                            //                        listaContactosDto.remove(contacto);
                        }
                    } else {
                        ContactoEmergenciaEntity contactoEmerg = genericMapper.mapContactoDtoToContactoEntity(contacto);
                        contactoEmerg.setUsuarioCreacion(username);
                        contactoEmerg.setFechaCreacion(CurrentTime.getTimestamp());
                        contactoEmerg.setEstado(Constants.STATUS_ACTIVE);

                        listaContactosEntities.add(contactoEmergenciaRepository.save(contactoEmerg));
                    }
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////Buscar DNI en Reniec///////////////////// ///////////////////////
        MsExternalToReniecResponse getInfoReniec = toMSExternalApi.getInfoExtReniec(requestPaciente.getNumDocumento());
        if (getInfoReniec.getApellidoMaterno() == null) {
            return new ResponseBase(406, "El documento " + requestPaciente.getNumDocumento() +
                    " no existe en Reniec.", null);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        DirecccionEntity direccion = genericMapper.mapDireccionDtoToDireccionEntity(requestPaciente.getDireccion());
        direccion.setUsuarioCreacion(username);
        direccion.setFechaCreacion(CurrentTime.getTimestamp());
        direccion.setEstado(Constants.STATUS_ACTIVE);
        direccion = direcccionRepository.save(direccion);



        PacienteEntity paciente = new PacienteEntity();
//        paciente = genericMapper.mapRequestPacienteToPacienteEntity(requestPaciente);
        paciente.setNumDocumento(requestPaciente.getNumDocumento());
        paciente.setGenero(requestPaciente.getGenero());
        paciente.setTelefono(requestPaciente.getTelefono());
        try {
            DateFormat formateador = new SimpleDateFormat("dd/M/yy");
            paciente.setFechaNacimiento(formateador.parse(requestPaciente.getFechaNacimiento()));
        } catch (Exception e) {
            return new ResponseBase(406,
                    "Formato de fecha incorrecto. Ingresar formato dd/MM/yyyy.", null);
        }

        paciente.setNombre(getInfoReniec.getNombres());
        paciente.setApellido(getInfoReniec.getApellidoPaterno() + " " + getInfoReniec.getApellidoMaterno());
        paciente.setUsuarioCreacion(username);
        paciente.setFechaCreacion(CurrentTime.getTimestamp());
        paciente.setEstado(Constants.STATUS_ACTIVE);
        paciente.setDireccion(direccion);
        if (!listaContactosEntities.isEmpty()) {
            paciente.setContactoEmergencia(listaContactosEntities);
        }
        paciente = pacienteRepository.save(paciente);

        return new ResponseBase(201,
                "Paciente registrado con exito." + mensajeContactos, paciente);

    }

    @Override
    public ResponseBase updatePacienteOut(RequestPaciente requestPaciente, String username) {
        return new ResponseBase(200, "Desde  PacienteAdapter/update, " +
                "registrado por "+username, null);
    }


    @Override
    public ResponseBase deletePacienteOut(String numDoc, String username) {
        Optional<PacienteEntity> findPaciente = pacienteRepository.findByNumDocumento(numDoc);
        if(findPaciente.isPresent()){
            findPaciente.get().setEstado(Constants.STATUS_INACTIVE);
            findPaciente.get().setUsuarioEliminacion(username);
            findPaciente.get().setFechaEliminacion(CurrentTime.getTimestamp());

            return new ResponseBase(202, "Registro eliminado",
                    pacienteRepository.save(findPaciente.get()));
        }
        return new ResponseBase(404, "No se encontro al doctor.", null);
    }
}
