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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveAccountPersistenceServiceTest {

    private SaveAccountPersistenceService service;
    private AccountRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = mock(AccountRepository.class);
        this.service = new SaveAccountPersistenceService(repository);
    }

    @Test
    @DisplayName("Given Account When Save Then Return Saved Account")
    public void givenAccountWhenSaveThenReturnSavedAccount(){

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

        when(repository.save(accountEntity)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));


        AccountEntity savedAccountEntity = this.service.save(accountEntity);

        assertThat(savedAccountEntity).isNotNull();
    }


}
