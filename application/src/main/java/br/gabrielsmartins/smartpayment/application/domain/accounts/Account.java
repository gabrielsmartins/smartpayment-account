package br.gabrielsmartins.smartpayment.application.domain.accounts;

import br.gabrielsmartins.smartpayment.application.domain.enums.AccountStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import lombok.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(setterPrefix = "with")
public class Account {

    private UUID id;
    private UUID customerId;
    private AccountTypeEnum type;
    private AccountStatusEnum status;
    private BigDecimal balance;

    @Setter(AccessLevel.NONE)
    private List<FinancialTransaction> transactions = new LinkedList<>();

    public Integer addTransaction(FinancialTransaction transaction){
        FinancialTransaction.FinancialTransactionId transactionId = transaction.getId();
        transactionId.setSource(this);
        this.transactions.add(transaction);
        return this.transactions.size();
    }

    public List<FinancialTransaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
