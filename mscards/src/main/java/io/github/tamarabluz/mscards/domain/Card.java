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
    private String nome;
    @Enumerated(EnumType.STRING)
    private CardFlag flag;
    private BigDecimal renda;
    private BigDecimal limitBasic;

    public Card(String nome, CardFlag flag, BigDecimal renda, BigDecimal limitBasic) {
        this.nome = nome;
        this.flag = flag;
        this.renda = renda;
        this.limitBasic = limitBasic;
    }
}
