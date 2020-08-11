package br.gabrielsmartins.smartpayment.application.port.out.transactions;

import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SearchFinancialTransactionPort {

    List<FinancialTransaction> findByAccountIdAndInterval(UUID accountId, LocalDateTime startDatetime, LocalDateTime endDatetime);

    List<FinancialTransaction> findByCustomerIdAndInterval(String customerId, LocalDateTime startDatetime, LocalDateTime endDatetime);


}
