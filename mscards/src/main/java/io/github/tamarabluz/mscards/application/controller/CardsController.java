package io.github.tamarabluz.mscards.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cards")
public class CardsController {

    @GetMapping
    public String status(){
        return "ok";
    }
}
