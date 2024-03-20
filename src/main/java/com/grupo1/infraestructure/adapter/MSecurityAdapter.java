package com.grupo1.infraestructure.adapter;

import com.grupo1.domain.aggregates.response.MsSecurityResponse;
import com.grupo1.domain.aggregates.response.ResponseBase;
import com.grupo1.domain.ports.out.VerifySecurityServiceIOut;
import com.grupo1.infraestructure.rest.client.ToSecurityClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MSecurityAdapter implements VerifySecurityServiceIOut {

    private final ToSecurityClient toSecurityClient;

    @Override
    public Optional<MsSecurityResponse> verifyByTokenOut(String token) {
        return toSecurityClient.verifyToken(token);
    }
}

