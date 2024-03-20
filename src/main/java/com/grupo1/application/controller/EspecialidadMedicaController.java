package com.grupo1.application.controller;


import com.grupo1.application.util.VerifyToken;
import com.grupo1.domain.aggregates.dto.EspecialidadMedicaDTO;
import com.grupo1.domain.aggregates.response.ResponseBase;
import com.grupo1.domain.ports.in.EspecialidadMedicaServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/especialidad")
@RequiredArgsConstructor
public class EspecialidadMedicaController {

    private final EspecialidadMedicaServiceIn especialidadMedicaServiceIn;
    private final VerifyToken verifyToken;

    @GetMapping("/{especialidad}")
    public ResponseBase findEspecialidad(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                             @PathVariable String especialidad){
        //Add roles autorizados aquí
        verifyToken.addRole("SYSTEM");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  especialidadMedicaServiceIn.findEspecialidadMedicaIn(especialidad);
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

    @GetMapping("/all")
    public ResponseBase findAllEnableEspecialidad(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        //Add roles autorizados aquí
        verifyToken.addRole("ADMIN");
        verifyToken.addRole("SYSTEM");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  especialidadMedicaServiceIn.findAllEnableEspecialidadMedicaIn();
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

    @PostMapping
    public ResponseBase registerEspecialidad(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                             @RequestBody EspecialidadMedicaDTO especialidadMedicaDTO){
        //Add roles autorizados aquí
        verifyToken.addRole("ADMIN");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  especialidadMedicaServiceIn.registerEspecialidadMedicaIn(especialidadMedicaDTO, username);
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

    @PutMapping
    public ResponseBase updateEspecialidad(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                       @RequestBody EspecialidadMedicaDTO especialidadMedicaDTO){
        //Add roles autorizados aquí
        verifyToken.addRole("ADMIN");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  especialidadMedicaServiceIn.updateEspecialidadMedicaIn(especialidadMedicaDTO, username);
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

    @DeleteMapping("/{id}")
    public ResponseBase deleteEspecialidad(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                       @PathVariable Long id){
        //Add roles autorizados aquí
        verifyToken.addRole("ADMIN");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  especialidadMedicaServiceIn.deleteEspecialidadMedicaIn(id, username);
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

}
