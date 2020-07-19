package br.gabrielsmartins.smartpayment.application.service.transactions;

import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import br.gabrielsmartins.smartpayment.application.port.in.transactions.SearchTransactionUseCase;
import br.gabrielsmartins.smartpayment.application.port.out.transactions.SearchTransactionPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class SearchTransactionService implements SearchTransactionUseCase {

    private final SearchTransactionPort port;

    @Override
    public List<FinancialTransaction> findByAccountIdAndInterval(UUID accountId, LocalDateTime start, LocalDateTime end) {
        return port.findByAccountIdAndInterval(accountId, start, end);
    }

    @Override
    public List<FinancialTransaction> findByCustomerIdAndInterval(UUID customerId, LocalDateTime start, LocalDateTime end) {
        return port.findByCustomerIdAndInterval(customerId, start, end);
    }
}
