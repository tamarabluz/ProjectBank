package io.github.tamarabluz.mscreditappraiser.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCards {
    private  String card;
    private String flag;
    private BigDecimal approvedLimit;

}
