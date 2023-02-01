package io.github.tamarabluz.mscreditappraiser.infra;

import io.github.tamarabluz.mscreditappraiser.domain.CustomerCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardsResourceCustomer {
    @GetMapping(params = "cpf")
    ResponseEntity<List<CustomerCard>> getCardsByCustomer(@RequestParam("cpf") String cpf);
}
