package com.grupo1.infraestructure.rest;


import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
public class ToSecurityError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        try {
            String getBody = this.getBody(response);
            log.error("FeignErrorDecoder HttpStatus:{} - Error Body: {}", response.status(), getBody);
            return new RuntimeException(getBody, new Throwable(getBody));
        } catch (Exception e) {
            log.error("FeignErrorDecoder Error Decoder en body de respuesta: ", e);
            return e;
        }
    }

    private String getBody(Response response) throws IOException {
        Response.Body body = response.body();
        if (!Objects.isNull(body)) {
            InputStream inputStream = body.asInputStream();
            final String bodyError = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            inputStream.close();
            return bodyError;
        } else {
            return StringUtils.EMPTY;
        }
    }

}
