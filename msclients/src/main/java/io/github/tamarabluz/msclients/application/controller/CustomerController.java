package io.github.tamarabluz.msclients.application.controller;

import io.github.tamarabluz.msclients.application.representation.CustomerSaveRequest;
import io.github.tamarabluz.msclients.domain.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public String status(){
        return "ok";
    }

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
