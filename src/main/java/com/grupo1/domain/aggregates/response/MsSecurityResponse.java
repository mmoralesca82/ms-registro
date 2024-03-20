package com.grupo1.domain.aggregates.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class MsSecurityResponse {

    private Integer code;
    private String message;
    private String username;
    private Set<String> roles;

}
