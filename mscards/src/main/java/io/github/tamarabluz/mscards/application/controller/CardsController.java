package io.github.tamarabluz.mscards.application.controller;

import io.github.tamarabluz.mscards.application.representation.CardSaveRequest;
import io.github.tamarabluz.mscards.application.service.CardService;
import io.github.tamarabluz.mscards.domain.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("cards")
public class CardsController {

    private final CardService service;

    @GetMapping
    public String status(){
        return "ok";
    }

    public ResponseEntity register(@RequestBody CardSaveRequest request){
        Card card = request.toModel();
        service.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
