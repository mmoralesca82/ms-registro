package com.grupo1.infraestructure.rest.client;




import com.grupo1.domain.aggregates.response.MsExternalToReniecResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "MS-EXTERNALAPI", url = "http://localhost:8000/ms-externalapi/v1")
public interface ToMSExternalApi {

    @GetMapping("/reniec/{numero}")
    MsExternalToReniecResponse getInfoExtReniec(@PathVariable String numero);

//    @GetMapping("/insurance/rimac")
//    MsExternalToHInsuranceRimacResponse getInfoExtRimac(@RequestParam String documento, @RequestParam String cobertura);

}
