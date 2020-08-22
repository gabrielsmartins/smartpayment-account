package br.gabrielsmartins.smartpayment.application.service.transactions;

import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import br.gabrielsmartins.smartpayment.application.port.in.transactions.SearchFinancialTransactionUseCase;
import br.gabrielsmartins.smartpayment.application.port.out.transactions.SearchFinancialTransactionPort;
import br.gabrielsmartins.smartpayment.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class SearchFinancialTransactionService implements SearchFinancialTransactionUseCase {

    private final SearchFinancialTransactionPort port;

    @Override
    public List<FinancialTransaction> findByAccountIdAndInterval(UUID accountId, LocalDateTime start, LocalDateTime end) {
        return port.findByAccountIdAndInterval(accountId, start, end);
    }

    @Override
    public List<FinancialTransaction> findByCustomerIdAndInterval(String customerId, LocalDateTime start, LocalDateTime end) {
        return port.findByCustomerIdAndInterval(customerId, start, end);
    }
}
