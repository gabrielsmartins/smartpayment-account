package br.gabrielsmartins.smartpayment.adapters.persistence.adapter.accounts;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions.FinancialTransactionEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountTypeDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.FinancialTransactionStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.accounts.AccountPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.accounts.AccountPersistenceMapperImpl;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.transactions.FinancialTransactionPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.transactions.FinancialTransactionPersistenceMapperImpl;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.accounts.SearchAccountPersistenceService;
import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchAccountPersistenceAdapterTest {

    private SearchAccountPersistenceAdapter adapter;
    private SearchAccountPersistenceService service;
    private FinancialTransactionPersistenceMapper transactionPersistenceMapper;
    private AccountPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.service = mock(SearchAccountPersistenceService.class);
        this.transactionPersistenceMapper = new FinancialTransactionPersistenceMapperImpl();
        this.mapper = new AccountPersistenceMapperImpl(transactionPersistenceMapper);
        this.adapter = new SearchAccountPersistenceAdapter(service, mapper);
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

        when(service.findById(any(UUID.class))).thenReturn(Optional.ofNullable(accountEntity));

        Optional<Account> optionalAccount = this.adapter.findById(accountEntity.getId());

        assertThat(optionalAccount).isPresent();
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

        when(service.findByCustomerId(anyString())).thenReturn(Collections.singletonList(accountEntity));


        List<Account> accounts = this.adapter.findByCustomerId(accountEntity.getCustomerId());

        assertThat(accounts).isNotEmpty();
    }

    @Test
    @DisplayName("Given Account Type When Exists Then Return Account List")
    public void givenAccountTypeIdWhenExistsThenReturnAccountList(){

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

        when(service.findByType(any(AccountTypeDataEnum.class))).thenReturn(Collections.singletonList(accountEntity));

        List<Account> accounts = this.adapter.findByType(accountEntity.getType().getEnumValue());

        assertThat(accounts).isNotEmpty();
    }


}
