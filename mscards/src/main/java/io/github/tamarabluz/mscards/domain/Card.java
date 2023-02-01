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
    @Column
    private String name;
    @Column
    @Enumerated(EnumType.STRING)
    private CardFlag flag;
    @Column
    private BigDecimal income;
    @Column
    private BigDecimal limitBasic;

    public Card(String name, CardFlag flag, BigDecimal income, BigDecimal limitBasic) {
        this.name = name;
        this.flag = flag;
        this.income = income;
        this.limitBasic = limitBasic;
    }


}
