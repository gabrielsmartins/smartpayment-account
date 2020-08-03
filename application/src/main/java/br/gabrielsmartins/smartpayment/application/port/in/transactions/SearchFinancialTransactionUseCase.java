package br.gabrielsmartins.smartpayment.application.port.in.transactions;

import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SearchFinancialTransactionUseCase {

    List<FinancialTransaction> findByAccountIdAndInterval(UUID accountId, LocalDateTime start, LocalDateTime end);

    List<FinancialTransaction> findByCustomerIdAndInterval(UUID customerId, LocalDateTime start, LocalDateTime end);


}
