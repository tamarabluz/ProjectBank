package io.github.tamarabluz.mscreditappraiser.application.service;

import io.github.tamarabluz.mscreditappraiser.domain.CustomerData;
import io.github.tamarabluz.mscreditappraiser.domain.CustomerSituation;
import io.github.tamarabluz.mscreditappraiser.infra.CustomerResourceCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final CustomerResourceCustomer customerResourceCustomer;

    public CustomerSituation getCustomerSituation(String cpf){
       ResponseEntity<CustomerData> customersDataResponse = customerResourceCustomer.customersData(cpf);
       return CustomerSituation
               .builder()
               .customer(customersDataResponse.getBody())
               .build();
    }
}
