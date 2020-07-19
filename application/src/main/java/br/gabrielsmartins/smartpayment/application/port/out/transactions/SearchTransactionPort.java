package br.gabrielsmartins.smartpayment.application.port.out.transactions;

import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SearchTransactionPort {

    List<FinancialTransaction> findByAccountIdAndInterval(UUID accountId, LocalDateTime start, LocalDateTime end);

    List<FinancialTransaction> findByCustomerIdAndInterval(UUID customerId, LocalDateTime start, LocalDateTime end);


}
