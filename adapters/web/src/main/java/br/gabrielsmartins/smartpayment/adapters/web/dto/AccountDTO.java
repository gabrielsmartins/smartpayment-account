package br.gabrielsmartins.smartpayment.adapters.web.dto;

import br.gabrielsmartins.smartpayment.application.domain.enums.AccountStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(value = "customer_id")
    private UUID customerId;

    @JsonProperty(value = "type")
    private AccountTypeEnum type;

    @JsonProperty(value = "status")
    private AccountStatusEnum status;

    @JsonProperty(value = "balance")
    private BigDecimal balance;

    @JsonProperty(value = "transactions")
    private List<FinancialTransaction> transactions = new LinkedList<>();


}
