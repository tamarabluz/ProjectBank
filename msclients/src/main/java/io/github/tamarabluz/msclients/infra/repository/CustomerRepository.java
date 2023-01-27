package io.github.tamarabluz.msclients.infra.repository;


import io.github.tamarabluz.msclients.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
