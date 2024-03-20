package com.grupo1.domain.ports.in;

import com.grupo1.domain.aggregates.response.MsSecurityResponse;

import java.util.Optional;

public interface VerifySecurityServiceIn {

  Optional<MsSecurityResponse> verifyByTokenIn(String token);

}
