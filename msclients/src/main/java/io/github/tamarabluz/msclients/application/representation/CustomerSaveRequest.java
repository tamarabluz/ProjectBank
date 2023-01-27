package io.github.tamarabluz.msclients.application.representation;

import io.github.tamarabluz.msclients.domain.entity.Customer;

public class CustomerSaveRequest {
    private String cpf;
    private String name;
    private Integer age;

    //converte objeto em customer
    public Customer toModel(){
        return new Customer(cpf, name, age);
    }

}
