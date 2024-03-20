package com.grupo1.application.util;

import com.grupo1.domain.aggregates.response.MsSecurityResponse;
import com.grupo1.domain.ports.in.VerifySecurityServiceIn;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Configuration
@Setter
@Getter
@RequiredArgsConstructor
public class VerifyToken {

    private final VerifySecurityServiceIn verifySecurityServiceIn;
    private Set<String> roles = new HashSet<>();

    public void addRole(String setRol){
        roles.add(setRol);
    }
    public String verifyToken(String token){

        try {
            Optional<MsSecurityResponse> verifyToken = verifySecurityServiceIn.verifyByTokenIn(token);
            if (verifyToken.isPresent()) {
                for (String role : verifyToken.get().getRoles()) {
                    for(String rol : roles) {
                        if (role.equals(rol)){
                            roles.clear();
                            return verifyToken.get().getUsername();
                        }
                    }
                }
            }
            roles.clear();
            return null;
        }
        catch(Exception e){
            roles.clear();
            return null;
        }
    }

}
