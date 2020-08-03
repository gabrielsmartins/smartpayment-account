package br.gabrielsmartins.smartpayment.adapters.persistence.repository.accounts;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions.FinancialTransactionEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountTypeDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.FinancialTransactionStatusDataEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

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
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID())
                .withStatus(AccountStatusDataEnum.ACTIVE)
                .withType(AccountTypeDataEnum.FREE)
                .withTransactions(new LinkedList<>())
                .build();

        accountEntity.addTransaction(transactionEntity);

        AccountEntity savedAccount = repository.saveAndFlush(accountEntity);
        assertThat(savedAccount).isNotNull();
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
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID())
                .withStatus(AccountStatusDataEnum.ACTIVE)
                .withType(AccountTypeDataEnum.FREE)
                .withTransactions(new LinkedList<>())
                .build();

        accountEntity.addTransaction(transactionEntity);

        repository.saveAndFlush(accountEntity);

        Optional<AccountEntity> optionalAccountEntity = repository.findById(accountEntity.getId());

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
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID())
                .withStatus(AccountStatusDataEnum.ACTIVE)
                .withType(AccountTypeDataEnum.FREE)
                .withTransactions(new LinkedList<>())
                .build();

        accountEntity.addTransaction(transactionEntity);

        repository.saveAndFlush(accountEntity);

        List<AccountEntity> accounts = repository.findByCustomerId(accountEntity.getCustomerId());

        assertThat(accounts).isNotEmpty();
    }

    @Test
    @DisplayName("Given Account Type When Exists Then Return Account List")
    public void givenAccountTypedWhenExistsThenReturnAccountList(){
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
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID())
                .withStatus(AccountStatusDataEnum.ACTIVE)
                .withType(AccountTypeDataEnum.FREE)
                .withTransactions(new LinkedList<>())
                .build();

        accountEntity.addTransaction(transactionEntity);

        repository.saveAndFlush(accountEntity);

        List<AccountEntity> accounts = repository.findByType(accountEntity.getType());

        assertThat(accounts).isNotEmpty();
    }
}
