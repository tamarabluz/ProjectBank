package io.github.tamarabluz.mscards.application.representation;

import io.github.tamarabluz.mscards.domain.CustomerCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsForCustomerResponse {
    private String name;
    private String flag;
    private BigDecimal limitReleased;


    public static CardsForCustomerResponse fromModel(CustomerCard model){
        return new CardsForCustomerResponse(
                model.getCard().getName(),
                model.getCard().getFlag().toString(),
                model.getLimit());
    }
}
