package io.github.tamarabluz.mscards.application.controller;

import io.github.tamarabluz.mscards.application.representation.CardSaveRequest;
import io.github.tamarabluz.mscards.application.service.CardService;
import io.github.tamarabluz.mscards.domain.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cards")
public class CardsController {

    private final CardService service;

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequest request){
        Card card = request.toModel();
        service.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardIncomeUp(@RequestParam("income") Long income){
        List<Card> list = service.getEqualLesserIncomeCard(income);
        return ResponseEntity.ok(list);

    }
}
