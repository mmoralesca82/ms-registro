package com.grupo1.domain.impl;

import com.grupo1.domain.aggregates.response.MsSecurityResponse;
import com.grupo1.domain.ports.in.VerifySecurityServiceIn;
import com.grupo1.domain.ports.out.VerifySecurityServiceIOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerifySecurityServiceImpl implements VerifySecurityServiceIn {

    private  final VerifySecurityServiceIOut verifySecurityServiceIOut;

    @Override
    public Optional<MsSecurityResponse> verifyByTokenIn(String token) {
        return verifySecurityServiceIOut.verifyByTokenOut(token);
    }
}
