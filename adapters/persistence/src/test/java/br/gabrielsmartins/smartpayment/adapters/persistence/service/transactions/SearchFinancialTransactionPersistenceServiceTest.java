package br.gabrielsmartins.smartpayment.adapters.persistence.service.transactions;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions.FinancialTransactionEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.FinancialTransactionStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.transactions.FinancialTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchFinancialTransactionPersistenceServiceTest {

    private SearchFinancialTransactionPersistenceService service;
    private FinancialTransactionRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = mock(FinancialTransactionRepository.class);
        this.service = new SearchFinancialTransactionPersistenceService(repository);
    }

    @Test
    @DisplayName("Given Account Id And Interval Then Return Financial Transaction List")
    public void givenAccountIdAndIntervalThenReturnFinancialTransactionList(){
        UUID accountId = UUID.randomUUID();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();

        FinancialTransactionEntity transactionEntity = FinancialTransactionEntity.builder()
                .withId(FinancialTransactionEntity.FinancialTransactionEntityId.builder()
                        .withIdentifier(UUID.randomUUID())
                        .build())
                .withAccountBalance(new BigDecimal(1500))
                .withAmount(new BigDecimal(100))
                .withDescription("Foo")
                .withCreatedAt(LocalDateTime.now())
                .withStatus(FinancialTransactionStatusDataEnum.COMMITTED)
                .build();

        when(repository.findByAccountIdAndInterval(any(UUID.class), any(LocalDateTime.class), any(LocalDateTime.class)))
                                                    .thenReturn(Collections.singletonList(transactionEntity));

        List<FinancialTransactionEntity> transactions = this.service.findByAccountIdAndInterval(accountId, startDate, endDate);
        assertThat(transactions).isNotNull();
    }

    @Test
    @DisplayName("Given Customer Id And Interval Then Return Financial Transaction List")
    public void givenCustomerIdAndIntervalThenReturnFinancialTransactionList(){
        String customerId = UUID.randomUUID().toString();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();

        FinancialTransactionEntity transactionEntity = FinancialTransactionEntity.builder()
                .withId(FinancialTransactionEntity.FinancialTransactionEntityId.builder()
                        .withIdentifier(UUID.randomUUID())
                        .build())
                .withAccountBalance(new BigDecimal(1500))
                .withAmount(new BigDecimal(100))
                .withDescription("Foo")
                .withCreatedAt(LocalDateTime.now())
                .withStatus(FinancialTransactionStatusDataEnum.COMMITTED)
                .build();

        when(repository.findByCustomerIdAndInterval(anyString(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(transactionEntity));

        List<FinancialTransactionEntity> transactions = this.service.findByCustomerIdAndInterval(customerId, startDate, endDate);
        assertThat(transactions).isNotNull();
    }
}
