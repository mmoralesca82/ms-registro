package com.grupo1.application.controller;


import com.grupo1.application.util.VerifyToken;
import com.grupo1.domain.aggregates.request.RequestDoctor;
import com.grupo1.domain.aggregates.response.ResponseBase;
import com.grupo1.domain.ports.in.DoctorServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorServiceIn doctorServiceIn;
    private final VerifyToken verifyToken;

    @GetMapping("/{numDoc}")
    public ResponseBase buscarDoctor(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                     @PathVariable String numDoc){
        //Add roles autorizados aquí
        verifyToken.addRole("SYSTEM");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  doctorServiceIn.buscarDoctorIn(numDoc);
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

    @GetMapping("/all")
    public ResponseBase buscarAllEnableDoctor(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        //Add roles autorizados aquí
        verifyToken.addRole("ADMIN");
        verifyToken.addRole("SYSTEM");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  doctorServiceIn.buscarAllEnableDoctorIn();
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

    @PostMapping
    public ResponseBase registerDoctor(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                       @RequestBody RequestDoctor requestDoctor){
        //Add roles autorizados aquí
        verifyToken.addRole("ADMIN");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  doctorServiceIn.registerDoctorIn(requestDoctor, username);
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

    @PutMapping
    public ResponseBase updateDoctor(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                       @RequestBody RequestDoctor requestDoctor){
        //Add roles autorizados aquí
        verifyToken.addRole("ADMIN");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  doctorServiceIn.updateDoctorIn(requestDoctor, username);
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

    @DeleteMapping("/{numDoc}")
    public ResponseBase deleteDoctor(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                       @PathVariable String numDoc){
        //Add roles autorizados aquí
        verifyToken.addRole("ADMIN");
        //////////////////////////
        String username = verifyToken.verifyToken(token);
        if(username != null){
            return  doctorServiceIn.deleteDoctorIn(numDoc, username);
        }
        return new ResponseBase(403,"No autorizado.",null);
    }

}
