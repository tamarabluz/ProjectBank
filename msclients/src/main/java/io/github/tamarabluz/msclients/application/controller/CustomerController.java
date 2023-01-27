package io.github.tamarabluz.msclients.application.controller;

import io.github.tamarabluz.msclients.application.representation.CustomerSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public String status(){
        log.info("Get customer microservice status");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CustomerSaveRequest request){
       var customer = request.toModel();
       service.save(customer);
       URI headerLocation = ServletUriComponentsBuilder
               .fromCurrentRequest()
               .query("cpf={cpf}")
               .buildAndExpand(customer.getCpf())
               .toUri();
       return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity customersData(@RequestParam("cpf") String cpf){
        var customer = service.getByCPF(cpf);
        if(customer.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

}
