package br.gabrielsmartins.smartpayment.application.domain.transactions;

import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.FinancialTransactionStatusEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(setterPrefix = "with")
@Getter
@Setter
public class FinancialTransaction {

    private FinancialTransactionId id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String description;
    private BigDecimal amount;
    private BigDecimal accountBalance;
    private FinancialTransactionStatusEnum status;
    private Account target;

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder(setterPrefix = "with")
    @Getter
    @Setter
    public static class FinancialTransactionId{
        private Account source;
        private UUID identifier;
    }


}
