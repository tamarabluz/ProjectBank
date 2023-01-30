package io.github.tamarabluz.mscards.application.service;

import io.github.tamarabluz.mscards.domain.CustomerCard;
import io.github.tamarabluz.mscards.infra.CustomerCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerCardService {

    private final CustomerCardRepository repository;

    public List<CustomerCard> listCardByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
