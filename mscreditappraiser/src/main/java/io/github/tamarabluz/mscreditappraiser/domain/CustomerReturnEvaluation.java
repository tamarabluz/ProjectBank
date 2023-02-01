package io.github.tamarabluz.mscreditappraiser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerReturnEvaluation {
    private List<ApprovedCards>  cards;

}
