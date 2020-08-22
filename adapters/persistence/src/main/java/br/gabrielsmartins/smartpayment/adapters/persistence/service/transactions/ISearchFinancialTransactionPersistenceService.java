package br.gabrielsmartins.smartpayment.adapters.persistence.service.transactions;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions.FinancialTransactionEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ISearchFinancialTransactionPersistenceService {

    List<FinancialTransactionEntity> findByAccountIdAndInterval(UUID accountId, LocalDateTime startDatetime, LocalDateTime endDatetime);

    List<FinancialTransactionEntity> findByCustomerIdAndInterval(String customerId, LocalDateTime startDatetime, LocalDateTime endDatetime);

}
