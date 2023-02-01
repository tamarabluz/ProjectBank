package io.github.tamarabluz.mscreditappraiser.application.controller;

import io.github.tamarabluz.mscreditappraiser.application.exception.CustomerDataNotFoundException;
import io.github.tamarabluz.mscreditappraiser.application.exception.MicroservicesCommunicationErrorException;
import io.github.tamarabluz.mscreditappraiser.application.service.CreditAppraiserService;
import io.github.tamarabluz.mscreditappraiser.domain.CustomerReturnEvaluation;
import io.github.tamarabluz.mscreditappraiser.domain.CustomerSituation;
import io.github.tamarabluz.mscreditappraiser.domain.EvaluationData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit-appraiser")
public class CreditAppraiserController {

    private final CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "customer-situation", params = "cpf")
    public ResponseEntity getCustomerSituation(@RequestParam("cpf") String cpf) {
        try {
            CustomerSituation customerSituation = creditAppraiserService.getCustomerSituation(cpf);
            return ResponseEntity.ok(customerSituation);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroservicesCommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity carryOutEvaluation(@RequestBody EvaluationData data) {
        try {
            CustomerReturnEvaluation customerReturnEvaluation = creditAppraiserService
                    .carryOutEvaluation(data.getCpf(), data.getIncome());
            return ResponseEntity.ok(customerReturnEvaluation);

        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroservicesCommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}