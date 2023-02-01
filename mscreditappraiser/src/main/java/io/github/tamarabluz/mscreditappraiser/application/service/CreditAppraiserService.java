package io.github.tamarabluz.mscreditappraiser.application.service;

import feign.FeignException;
import io.github.tamarabluz.mscreditappraiser.application.exception.CustomerDataNotFoundException;
import io.github.tamarabluz.mscreditappraiser.application.exception.MicroservicesCommunicationErrorException;
import io.github.tamarabluz.mscreditappraiser.domain.*;
import io.github.tamarabluz.mscreditappraiser.infra.CardsResourceCustomer;
import io.github.tamarabluz.mscreditappraiser.infra.CustomerResourceCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final CustomerResourceCustomer customerResourceCustomer;
    private final CardsResourceCustomer cardsResourceCustomer;

    public CustomerSituation getCustomerSituation(String cpf)
            throws CustomerDataNotFoundException, MicroservicesCommunicationErrorException {
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

    public CustomerReturnEvaluation carryOutEvaluation(String cpf, Long income)
            throws CustomerDataNotFoundException, MicroservicesCommunicationErrorException{
        try {
            ResponseEntity<CustomerData> customerDataResponse = customerResourceCustomer.customersData(cpf);
            ResponseEntity<List<Card>> cardResponse = cardsResourceCustomer.getCardIncomeUp(income);
            List<Card> cards = cardResponse.getBody();
            var cardsApprovedList = cards.stream().map(card -> {
                CustomerData customerData = customerDataResponse.getBody();

                BigDecimal limitBasic = card.getLimitBasic();
                BigDecimal ageBD = BigDecimal.valueOf(customerData.getAge());
                var factor = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal limitApproved = factor.multiply(limitBasic);


                ApprovedCards approvedCards = new ApprovedCards();
                approvedCards.setCard(card.getName());
                approvedCards.setFlag(card.getFlag());
                approvedCards.setApprovedLimit(limitApproved);

                return approvedCards;

            }).collect(Collectors.toList());

            return new CustomerReturnEvaluation(cardsApprovedList);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new CustomerDataNotFoundException();
            }
            throw new MicroservicesCommunicationErrorException(e.getMessage(), status);
        }
    }
        }
