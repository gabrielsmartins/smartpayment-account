package br.gabrielsmartins.smartpayment.adapters.persistence.adapter.transactions;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions.FinancialTransactionEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.transactions.FinancialTransactionPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.transactions.SearchFinancialTransactionPersistenceService;
import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import br.gabrielsmartins.smartpayment.application.port.out.transactions.SearchFinancialTransactionPort;
import br.gabrielsmartins.smartpayment.common.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class SearchFinancialTransactionPersistenceAdapter implements SearchFinancialTransactionPort {

    private final SearchFinancialTransactionPersistenceService service;
    private final FinancialTransactionPersistenceMapper mapper;

    @Override
    public List<FinancialTransaction> findByAccountIdAndInterval(UUID accountId, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        List<FinancialTransactionEntity> transactionEntities = service.findByAccountIdAndInterval(accountId, startDatetime, endDatetime);
        return mapper.mapToDomain(transactionEntities);
    }

    @Override
    public List<FinancialTransaction> findByCustomerIdAndInterval(UUID customerId, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        List<FinancialTransactionEntity> transactionEntities = service.findByCustomerIdAndInterval(customerId, startDatetime, endDatetime);
        return mapper.mapToDomain(transactionEntities);
    }
}
