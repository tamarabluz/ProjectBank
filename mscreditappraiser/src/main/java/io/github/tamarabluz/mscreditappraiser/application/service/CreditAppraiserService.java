package io.github.tamarabluz.mscreditappraiser.application.service;

import feign.FeignException;
import io.github.tamarabluz.mscreditappraiser.application.exception.CustomerDataNotFoundException;
import io.github.tamarabluz.mscreditappraiser.application.exception.MicroservicesCommunicationErrorException;
import io.github.tamarabluz.mscreditappraiser.domain.CustomerCard;
import io.github.tamarabluz.mscreditappraiser.domain.CustomerData;
import io.github.tamarabluz.mscreditappraiser.domain.CustomerSituation;
import io.github.tamarabluz.mscreditappraiser.infra.CardsResourceCustomer;
import io.github.tamarabluz.mscreditappraiser.infra.CustomerResourceCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final CustomerResourceCustomer customerResourceCustomer;
    private final CardsResourceCustomer cardsResourceCustomer;

    public CustomerSituation getCustomerSituation(String cpf) throws CustomerDataNotFoundException, MicroservicesCommunicationErrorException {
        try {
            ResponseEntity<CustomerData> customersDataResponse = customerResourceCustomer.customersData(cpf);
            ResponseEntity<List<CustomerCard>> cardsResponse = cardsResourceCustomer.getCardsByCustomer(cpf);
            return CustomerSituation
                    .builder()
                    .customer(customersDataResponse.getBody())
                    .cards(cardsResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new CustomerDataNotFoundException();
            }
            throw new MicroservicesCommunicationErrorException(e.getMessage(), status);
        }
    }
}