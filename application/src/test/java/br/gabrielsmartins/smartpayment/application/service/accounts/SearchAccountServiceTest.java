package br.gabrielsmartins.smartpayment.application.service.accounts;

import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.FinancialTransactionStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import br.gabrielsmartins.smartpayment.application.port.out.accounts.SearchAccountPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchAccountServiceTest {

    private SearchAccountService service;
    private SearchAccountPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(SearchAccountPort.class);
        this.service = new SearchAccountService(port);
    }

    @Test
    @DisplayName("Given Account Id When Exists Then Return Account")
    public void givenAccountIdWhenExistsThenReturnAccount(){



        FinancialTransaction transaction = FinancialTransaction.builder()
                .withId(FinancialTransaction.FinancialTransactionId.builder()
                        .withIdentifier(UUID.randomUUID())
                        .build())
                .withAccountBalance(new BigDecimal(1500))
                .withAmount(new BigDecimal(100))
                .withDescription("Foo")
                .withStart(LocalDateTime.now())
                .withEnd(LocalDateTime.now())
                .withStatus(FinancialTransactionStatusEnum.COMMITTED)
                .build();

        Account account = Account.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID())
                .withStatus(AccountStatusEnum.ACTIVE)
                .withType(AccountTypeEnum.FREE)
                .withTransactions(Arrays.asList(transaction))
                .build();

        when(port.findById(any(UUID.class))).thenReturn(Optional.ofNullable(account));

        Optional<Account> optionalAccount = this.service.findById(UUID.randomUUID());
        assertThat(optionalAccount).isPresent();
    }


    @Test
    @DisplayName("Given Customer Id When Exists Then Return Account")
    public void givenCustomerIdWhenExistsThenReturnAccount(){


        FinancialTransaction transaction = FinancialTransaction.builder()
                .withId(FinancialTransaction.FinancialTransactionId.builder()
                        .withIdentifier(UUID.randomUUID())
                        .build())
                .withAccountBalance(new BigDecimal(1500))
                .withAmount(new BigDecimal(100))
                .withDescription("Foo")
                .withStart(LocalDateTime.now())
                .withEnd(LocalDateTime.now())
                .withStatus(FinancialTransactionStatusEnum.COMMITTED)
                .build();

        Account account = Account.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID())
                .withStatus(AccountStatusEnum.ACTIVE)
                .withType(AccountTypeEnum.FREE)
                .withTransactions(Arrays.asList(transaction))
                .build();

        when(port.findByCustomerId(any(UUID.class))).thenReturn(Optional.ofNullable(account));

        Optional<Account> optionalAccount = this.service.findByCustomerId(UUID.randomUUID());
        assertThat(optionalAccount).isPresent();
    }


    @Test
    @DisplayName("Given Account Type When Exists Then Return Account List")
    public void givenAccountTypeWhenExistsThenReturnAccountList(){


        FinancialTransaction transaction = FinancialTransaction.builder()
                .withId(FinancialTransaction.FinancialTransactionId.builder()
                        .withIdentifier(UUID.randomUUID())
                        .build())
                .withAccountBalance(new BigDecimal(1500))
                .withAmount(new BigDecimal(100))
                .withDescription("Foo")
                .withStart(LocalDateTime.now())
                .withEnd(LocalDateTime.now())
                .withStatus(FinancialTransactionStatusEnum.COMMITTED)
                .build();

        Account account = Account.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID())
                .withStatus(AccountStatusEnum.ACTIVE)
                .withType(AccountTypeEnum.FREE)
                .withTransactions(Arrays.asList(transaction))
                .build();

        when(port.findByType(any(AccountTypeEnum.class))).thenReturn(Arrays.asList(account));

        List<Account> accounts = this.service.findByType(AccountTypeEnum.FREE);
        assertThat(accounts).isNotEmpty();
    }
}
