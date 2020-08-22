package br.gabrielsmartins.smartpayment.adapters.persistence.service.accounts;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions.FinancialTransactionEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountTypeDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.FinancialTransactionStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.accounts.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchAccountPersistenceServiceTest {

    private SearchAccountPersistenceService service;
    private AccountRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = mock(AccountRepository.class);
        this.service = new SearchAccountPersistenceService(repository);
    }

    @Test
    @DisplayName("Given Account Id When Exists Then Return Account")
    public void givenAccountIdWhenExistsThenReturnAccount(){
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

        AccountEntity accountEntity = AccountEntity.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID().toString())
                .withStatus(AccountStatusDataEnum.ACTIVE)
                .withType(AccountTypeDataEnum.FREE)
                .build();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(accountEntity));

        Optional<AccountEntity> optionalAccountEntity = service.findById(accountEntity.getId());

        assertThat(optionalAccountEntity).isPresent();
    }

    @Test
    @DisplayName("Given Customer Id When Exists Then Return Account")
    public void givenCustomerIdWhenExistsThenReturnAccount(){
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

        AccountEntity accountEntity = AccountEntity.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID().toString())
                .withStatus(AccountStatusDataEnum.ACTIVE)
                .withType(AccountTypeDataEnum.FREE)
                .build();


        when(repository.findByCustomerId(anyString())).thenReturn(Collections.singletonList(accountEntity));

        List<AccountEntity> accounts = service.findByCustomerId(accountEntity.getCustomerId());

        assertThat(accounts).isNotEmpty();
    }

    @Test
    @DisplayName("Given Account Type When Exists Then Return Account List")
    public void givenAccounTypedWhenExistsThenReturnAccountList(){
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

        AccountEntity accountEntity = AccountEntity.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID().toString())
                .withStatus(AccountStatusDataEnum.ACTIVE)
                .withType(AccountTypeDataEnum.FREE)
                .build();


        when(repository.findByType(any(AccountTypeDataEnum.class))).thenReturn(Collections.singletonList(accountEntity));


        List<AccountEntity> accounts = service.findByType(accountEntity.getType());

        assertThat(accounts).isNotEmpty();
    }
}
