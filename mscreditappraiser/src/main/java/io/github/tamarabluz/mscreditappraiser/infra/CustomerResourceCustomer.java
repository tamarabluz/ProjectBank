package io.github.tamarabluz.mscreditappraiser.infra;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscustomers", path = "/customers")
public interface CustomerResourceCustomer {

    @GetMapping(params = "cpf")
    ResponseEntity customersData(@RequestParam("cpf") String cpf);


}
