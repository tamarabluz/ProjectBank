package io.github.tamarabluz.mscards.application.service;

import io.github.tamarabluz.mscards.domain.Card;
import io.github.tamarabluz.mscards.infra.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository repository;

    @Transactional
    public Card save(Card card){
        return repository.save(card);
    }

    public List<Card> getEqualLesserIncomeCard(Long income){
        var incomeBigDecimal = BigDecimal.valueOf(income);
        return repository.findByIncomeLessThanEqual(incomeBigDecimal);

    }

}
