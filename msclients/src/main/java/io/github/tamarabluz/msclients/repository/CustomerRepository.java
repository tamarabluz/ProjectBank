package io.github.tamarabluz.msclients.repository;


import io.github.tamarabluz.msclients.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
