package br.gabrielsmartins.smartpayment.application.service.transactions;

import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.FinancialTransactionStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import br.gabrielsmartins.smartpayment.application.port.out.transactions.SearchFinancialTransactionPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchFinancialTransactionServiceTest {

    private SearchFinancialTransactionService service;
    private SearchFinancialTransactionPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(SearchFinancialTransactionPort.class);
        this.service = new SearchFinancialTransactionService(port);
    }

    @Test
    @DisplayName("Given Account Id And Interval When Exists Then Return Transaction")
    public void givenAccountIdAndIntervalWhenExistsThenReturnTransaction(){

        FinancialTransaction transaction = FinancialTransaction.builder()
                .withId(FinancialTransaction.FinancialTransactionId.builder()
                        .withIdentifier(UUID.randomUUID())
                        .build())
                .withAccountBalance(new BigDecimal(1500))
                .withAmount(new BigDecimal(100))
                .withDescription("Foo")
                .withCreatedAt(LocalDateTime.now())
                .withStatus(FinancialTransactionStatusEnum.COMMITTED)
                .build();

        Account account = Account.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID())
                .withStatus(AccountStatusEnum.ACTIVE)
                .withType(AccountTypeEnum.FREE)
                .build();

        when(port.findByAccountIdAndInterval(any(UUID.class), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(Arrays.asList(transaction));

        LocalDateTime startDatetime = transaction.getCreatedAt().minusHours(1);
        LocalDateTime endDatetime = transaction.getCreatedAt().plusHours(1);

        List<FinancialTransaction> transactions = this.service.findByAccountIdAndInterval(account.getCustomerId(), startDatetime, endDatetime);
        assertThat(transactions).isNotEmpty();
    }


    @Test
    @DisplayName("Given Customer Id And Interval When Exists Then Return Transaction")
    public void givenCustomerIdAndIntervalWhenExistsThenReturnTransaction(){

        FinancialTransaction transaction = FinancialTransaction.builder()
                .withId(FinancialTransaction.FinancialTransactionId.builder()
                        .withIdentifier(UUID.randomUUID())
                        .build())
                .withAccountBalance(new BigDecimal(1500))
                .withAmount(new BigDecimal(100))
                .withDescription("Foo")
                .withCreatedAt(LocalDateTime.now())
                .withStatus(FinancialTransactionStatusEnum.COMMITTED)
                .build();

        Account account = Account.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID())
                .withStatus(AccountStatusEnum.ACTIVE)
                .withType(AccountTypeEnum.FREE)
                .build();

        when(port.findByCustomerIdAndInterval(any(UUID.class), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(Arrays.asList(transaction));

        LocalDateTime startDatetime = transaction.getCreatedAt().minusHours(1);
        LocalDateTime endDatetime = transaction.getCreatedAt().plusHours(1);

        List<FinancialTransaction> transactions = this.service.findByCustomerIdAndInterval(account.getCustomerId(), startDatetime, endDatetime);
        assertThat(transactions).isNotEmpty();
    }

}
