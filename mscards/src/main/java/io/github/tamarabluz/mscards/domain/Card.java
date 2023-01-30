package io.github.tamarabluz.mscards.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CardFlag flag;
    private BigDecimal income;
    private BigDecimal limitBasic;

    public Card(String name, CardFlag flag, BigDecimal income, BigDecimal limitBasic) {
        this.name = name;
        this.flag = flag;
        this.income = income;
        this.limitBasic = limitBasic;
    }


}
