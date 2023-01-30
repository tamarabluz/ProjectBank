package io.github.tamarabluz.mscards.application.controller;

import io.github.tamarabluz.mscards.application.representation.CardSaveRequest;
import io.github.tamarabluz.mscards.application.representation.CardsForCustomerResponse;
import io.github.tamarabluz.mscards.application.service.CardService;
import io.github.tamarabluz.mscards.application.service.CustomerCardService;
import io.github.tamarabluz.mscards.domain.Card;
import io.github.tamarabluz.mscards.domain.CustomerCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("cards")
public class CardsController {

    private final CardService cardService;
    private final CustomerCardService customerCardService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequest request){
        Card card = request.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardIncomeUp(@RequestParam("income") Long income){
        List<Card> list = cardService.getEqualLesserIncomeCard(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    private ResponseEntity<List<CardsForCustomerResponse>> getCardsByCustomer(@RequestParam("cpf") String cpf){
        List<CustomerCard> list = customerCardService.listCardByCpf(cpf);
        List<CardsForCustomerResponse> resultList = list.stream()
                .map(CardsForCustomerResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
