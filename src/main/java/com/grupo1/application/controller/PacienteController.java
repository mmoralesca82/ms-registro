package com.grupo1.application.controller;

import com.grupo1.application.util.VerifyToken;
import com.grupo1.domain.aggregates.request.RequestPaciente;
import com.grupo1.domain.aggregates.response.ResponseBase;
import com.grupo1.domain.ports.in.PacienteServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteServiceIn pacienteServiceIn;
    private final VerifyToken verifyToken;

    @GetMapping("/{numDoc}")
    public ResponseBase buscarPaciente(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                     @PathVariable String numDoc){
        //Add roles autorizados aquí
        verifyToken.addRole("SYSTEM");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  pacienteServiceIn.buscarDoctorIn(numDoc);
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

    @GetMapping("/all")
    public ResponseBase buscarAllEnableDoctor(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        //Add roles autorizados aquí
        verifyToken.addRole("USER");
        verifyToken.addRole("ADMIN");
        verifyToken.addRole("SYSTEM");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  pacienteServiceIn.buscarAllEnableDoctorIn();
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

    @PostMapping
    public ResponseBase registerPaciente(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                         @RequestBody RequestPaciente requestPaciente){
        //Add roles autorizados aquí
        verifyToken.addRole("USER");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  pacienteServiceIn.registerPacienteIn(requestPaciente, username);
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

    @PutMapping
    public ResponseBase updatePaciente(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                         @RequestBody RequestPaciente requestPaciente){
        //Add roles autorizados aquí
        verifyToken.addRole("USER");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  pacienteServiceIn.updatePacienteIn(requestPaciente, username);
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

    @DeleteMapping("/{numDoc}")
    public ResponseBase deletePaciente(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                          @PathVariable String numDoc){
        //Add roles autorizados aquí
        verifyToken.addRole("ADMIN");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  pacienteServiceIn.deletePacienteIn(numDoc, username);
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

}
