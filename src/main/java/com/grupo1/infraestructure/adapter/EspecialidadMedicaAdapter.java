package com.grupo1.infraestructure.adapter;

import com.grupo1.domain.aggregates.constants.CoberturasSeguro;
import com.grupo1.domain.aggregates.constants.Constants;
import com.grupo1.domain.aggregates.dto.EspecialidadMedicaDTO;
import com.grupo1.domain.aggregates.response.ResponseBase;
import com.grupo1.domain.ports.out.EspecialidadMedicaServiceOut;
import com.grupo1.infraestructure.entity.EspecialidadMedicaEntity;
import com.grupo1.infraestructure.mapper.GenericMapper;
import com.grupo1.infraestructure.repository.EspecialidadMedicaRepository;
import com.grupo1.infraestructure.util.CurrentTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecialidadMedicaAdapter implements EspecialidadMedicaServiceOut {

    private  final EspecialidadMedicaRepository especialidadMedicaRepository;
    private final GenericMapper genericMapper;

    @Override
    public ResponseBase findEspecialidadMedicaOut(String especialidad) {
        Optional<EspecialidadMedicaEntity> getEspecialidad = especialidadMedicaRepository.findByEspecialidad(especialidad);
        if (getEspecialidad.isPresent()) {
            return new ResponseBase(302, "Informacion encotrada con exito", getEspecialidad);
        }
        return new ResponseBase(404, "No se encontro la informacion", null);
    }

    @Override
    public ResponseBase findAllEnableEspecialidadMedicaOut() {
        List<EspecialidadMedicaDTO> especialidadDTOList = new ArrayList<>();
        List<EspecialidadMedicaEntity> especialidadEntities = especialidadMedicaRepository.findByEstado(true);
        for(EspecialidadMedicaEntity especialidad : especialidadEntities){
            EspecialidadMedicaDTO especialidadDTO = genericMapper.mapEspecialidadEntityToEspecialidadDTO(especialidad);
            especialidadDTOList.add(especialidadDTO );
        }
        return  new ResponseBase(302, "Informacion encotrada con exito", especialidadDTOList);
    }

    @Override
    public ResponseBase registerEspecialidadMedicaOut(EspecialidadMedicaDTO especialidadMedicaDTO, String username) {
        boolean coberturaValida = false;
        for(String cob : CoberturasSeguro.coberturaMED) {
            if (cob.equals(especialidadMedicaDTO.getComplejidad())){
                coberturaValida = true;
                break;
            }
        }
        if((especialidadMedicaDTO.getEspecialidad()==null ||
                especialidadMedicaDTO.getEspecialidad().isEmpty()) || !coberturaValida){
            return new ResponseBase(406, "Error en la informacion", null);
        }
        Optional<EspecialidadMedicaEntity> getEspecialidad = especialidadMedicaRepository.
                findByEspecialidad(especialidadMedicaDTO.getEspecialidad());
        if (getEspecialidad.isPresent()) {
            return new ResponseBase(406, "La especialidad ya existe", null);
        }
        EspecialidadMedicaEntity especialidadMedica = new EspecialidadMedicaEntity();
        especialidadMedica.setEspecialidad(especialidadMedicaDTO.getEspecialidad());
        especialidadMedica.setComplejidad(especialidadMedicaDTO.getComplejidad());
        especialidadMedica.setUsuarioCreacion(username);
        especialidadMedica.setFechaCreacion(CurrentTime.getTimestamp());
        especialidadMedica.setEstado(Constants.STATUS_ACTIVE);

        especialidadMedica = especialidadMedicaRepository.save(especialidadMedica);

        return new ResponseBase(201, "Registrado con exito.", especialidadMedica);
    }

    @Override
    public ResponseBase updateEspecialidadMedicaOut(EspecialidadMedicaDTO especialidadMedicaDTO, String username) {
       Optional<EspecialidadMedicaEntity> findEspecialidad = especialidadMedicaRepository.
               findByEspecialidad(especialidadMedicaDTO.getEspecialidad());
       if(findEspecialidad.isPresent()){
           boolean coberturaValida = false;
           for(String cob : CoberturasSeguro.coberturaMED) {
               if (cob.equals(especialidadMedicaDTO.getComplejidad())){
                   coberturaValida = true;
                   break;
               }
           }
           if(coberturaValida){
                findEspecialidad.get().setComplejidad(especialidadMedicaDTO.getComplejidad());
                findEspecialidad.get().setUsuarioModificacion(username);
                findEspecialidad.get().setFechaModificacion(CurrentTime.getTimestamp());
                return new ResponseBase(200, "Registro actualizado",
                        especialidadMedicaRepository.save(findEspecialidad.get()));
           } return new ResponseBase(404, "Complejidad no existe.", null);

       }
        return new ResponseBase(404, "No se encontro la especialidad.", null);
    }

    @Override
    public ResponseBase deleteEspecialidadMedicaOut(Long id, String username) {
        Optional<EspecialidadMedicaEntity> findEspecialidad = especialidadMedicaRepository.findById(id);
        if(findEspecialidad.isPresent()){
            findEspecialidad.get().setEstado(Constants.STATUS_INACTIVE);
            findEspecialidad.get().setUsuarioEliminacion(username);
            findEspecialidad.get().setFechaEliminacion(CurrentTime.getTimestamp());

            return new ResponseBase(202, "Registro eliminado",
                    especialidadMedicaRepository.save(findEspecialidad.get()));
        }
        return new ResponseBase(404, "No se encontro la especialidad.", null);
    }
}
