package io.github.tamarabluz.mscreditappraiser.application.controller;

import io.github.tamarabluz.mscreditappraiser.application.service.CreditAppraiserService;
import io.github.tamarabluz.mscreditappraiser.domain.CustomerSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit-appraiser")
public class CreditAppraiserController {

    private final CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "customer-situation", params = "cpf")
    public ResponseEntity<CustomerSituation> getCustomerSituation(@RequestParam("cpf") String cpf){
        CustomerSituation customerSituation = creditAppraiserService.getCustomerSituation(cpf);
        return ResponseEntity.ok(customerSituation);
    }
}
