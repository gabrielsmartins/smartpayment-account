package br.gabrielsmartins.smartpayment.adapters.persistence.adapter.accounts;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.accounts.AccountPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.accounts.AccountPersistenceMapperImpl;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.transactions.FinancialTransactionPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.transactions.FinancialTransactionPersistenceMapperImpl;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.accounts.SaveAccountPersistenceService;
import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.FinancialTransactionStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveAccountPersistenceAdapterTest {

    private SaveAccountPersistenceAdapter adapter;
    private SaveAccountPersistenceService service;
    private AccountPersistenceMapper mapper;
    private FinancialTransactionPersistenceMapper transactionPersistenceMapper;

    @BeforeEach
    public void setup(){
        this.service = mock(SaveAccountPersistenceService.class);
        this.transactionPersistenceMapper = new FinancialTransactionPersistenceMapperImpl();
        this.mapper = new AccountPersistenceMapperImpl(transactionPersistenceMapper);
        this.adapter = new SaveAccountPersistenceAdapter(service,mapper);
    }

    @Test
    @DisplayName("Given Account When Save Then Return Saved Account")
    public void givenAccountWhenSaveThenReturnSavedAccount(){

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
                .withCustomerId(UUID.randomUUID().toString())
                .withStatus(AccountStatusEnum.ACTIVE)
                .withType(AccountTypeEnum.FREE)
                .build();

        when(service.save(any(AccountEntity.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Account savedAccount = this.adapter.save(account);

        assertThat(savedAccount).isNotNull();
    }

}
