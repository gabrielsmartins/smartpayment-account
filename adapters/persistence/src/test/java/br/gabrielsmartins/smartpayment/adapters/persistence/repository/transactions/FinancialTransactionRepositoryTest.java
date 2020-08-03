package br.gabrielsmartins.smartpayment.adapters.persistence.repository.transactions;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions.FinancialTransactionEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountTypeDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.FinancialTransactionStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.accounts.AccountRepository;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FinancialTransactionRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FinancialTransactionRepository repository;

    @Test
    @DisplayName("Given Account Id And Interval Then Return Financial Transaction List")
    public void givenAccountIdAndIntervalThenReturnFinancialTransactionList(){
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

        this.accountRepository.save(accountEntity);

        UUID accountId = accountEntity.getId();
        LocalDateTime startDatetime = transactionEntity.getCreatedAt().minusHours(1);
        LocalDateTime endDatetime = transactionEntity.getCreatedAt().plusHours(1);

        List<FinancialTransactionEntity> transactions = this.repository.findByAccountIdAndInterval(accountId,startDatetime,endDatetime);
        assertThat(transactions).isNotNull();
    }

    @Test
    @DisplayName("Given Customer Id And Interval Then Return Financial Transaction List")
    public void givenCustomerIdAndIntervalThenReturnFinancialTransactionList(){
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

        this.accountRepository.save(accountEntity);

        UUID accountId = accountEntity.getId();
        LocalDateTime startDatetime = transactionEntity.getCreatedAt().minusHours(1);
        LocalDateTime endDatetime = transactionEntity.getCreatedAt().plusHours(1);


        List<FinancialTransactionEntity> transactions = this.repository.findByCustomerIdAndInterval(accountId, startDatetime, endDatetime);
        assertThat(transactions).isNotNull();
    }
}
