package io.github.tamarabluz.msclients.application.controller;

import io.github.tamarabluz.msclients.domain.entity.Customer;
import io.github.tamarabluz.msclients.infra.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    @Transactional
    public Customer save(Customer customer){
        return repository.save(customer);
    }

    public Optional<Customer> getByCPF(String cpf){
        return repository.findByCpf(cpf);
    }
}
