package br.gabrielsmartins.smartpayment.adapters.persistence.service.transactions;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions.FinancialTransactionEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.transactions.FinancialTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchFinancialTransactionPersistenceService implements ISearchFinancialTransactionPersistenceService {

    private final FinancialTransactionRepository repository;

    @Override
    public List<FinancialTransactionEntity> findByAccountIdAndInterval(UUID accountId, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        return repository.findByAccountIdAndInterval(accountId, startDatetime, endDatetime);
    }

    @Override
    public List<FinancialTransactionEntity> findByCustomerIdAndInterval(UUID customerId, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        return repository.findByCustomerIdAndInterval(customerId, startDatetime, endDatetime);
    }

}
