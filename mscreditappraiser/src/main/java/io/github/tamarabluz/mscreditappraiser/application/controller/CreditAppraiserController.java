package io.github.tamarabluz.mscreditappraiser.application.controller;

import io.github.tamarabluz.mscreditappraiser.application.exception.CustomerDataNotFoundException;
import io.github.tamarabluz.mscreditappraiser.application.exception.MicroservicesCommunicationErrorException;
import io.github.tamarabluz.mscreditappraiser.application.service.CreditAppraiserService;
import io.github.tamarabluz.mscreditappraiser.domain.CustomerSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity getCustomerSituation(@RequestParam("cpf") String cpf){
        try {
            CustomerSituation customerSituation = creditAppraiserService.getCustomerSituation(cpf);
            return ResponseEntity.ok(customerSituation);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroservicesCommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }
}
