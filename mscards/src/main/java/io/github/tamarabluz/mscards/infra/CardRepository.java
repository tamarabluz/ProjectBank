package io.github.tamarabluz.mscards.infra;

import io.github.tamarabluz.mscards.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByIncomeLessThanEqual(BigDecimal renda);
}
