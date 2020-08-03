package br.gabrielsmartins.smartpayment.adapters.web.dto.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class FinancialTransactionDTO {

    @JsonProperty("account_id")
    private UUID accountId;

    @JsonProperty("identifier")
    private UUID identifier;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("description")
    private String description;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("account_balance")
    private BigDecimal accountBalance;

    @JsonProperty("status")
    private String status;

    @JsonProperty("account_target_id")
    private UUID accountTargetId;

}
