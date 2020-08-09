package br.gabrielsmartins.smartpayment.adapters.persistence.mapper.accounts;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions.FinancialTransactionEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountTypeDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.FinancialTransactionStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.transactions.FinancialTransactionPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.transactions.FinancialTransactionPersistenceMapperImpl;
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
import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountPersistenceMapperTest {

    private AccountPersistenceMapper mapper;
    private FinancialTransactionPersistenceMapper transactionPersistenceMapper;

    @BeforeEach
    public void setup(){
        this.transactionPersistenceMapper = new FinancialTransactionPersistenceMapperImpl();
        this.mapper = new AccountPersistenceMapperImpl(transactionPersistenceMapper);
    }

    @Test
    @DisplayName("Given Account Domain When Map Then Return Account Entity")
    public void givenAccountDomainWhenMapThenReturnAccountEntity(){

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

        AccountEntity accountEntity = this.mapper.mapToEntity(account);

        assertThat(accountEntity.getId()).isEqualTo(account.getId());
        assertThat(accountEntity.getBalance()).isEqualTo(account.getBalance());
        assertThat(accountEntity.getCustomerId()).isEqualTo(account.getCustomerId());
        assertThat(accountEntity.getStatus().getEnumValue()).isEqualTo(account.getStatus());
        assertThat(accountEntity.getType().getEnumValue()).isEqualTo(account.getType());
        assertThat(accountEntity.getTransactions().size()).isEqualTo(account.getTransactions().size());
    }

    @Test
    @DisplayName("Given Account Entity When Map Then Return Account Domain")
    public void givenAccountEntityWhenMapThenReturnAccountDomain(){

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
                .withCustomerId(UUID.randomUUID())
                .withStatus(AccountStatusDataEnum.ACTIVE)
                .withType(AccountTypeDataEnum.FREE)
                .build();

        Account account = this.mapper.mapToDomain(accountEntity);

        assertThat(account.getId()).isEqualTo(accountEntity.getId());
        assertThat(account.getBalance()).isEqualTo(accountEntity.getBalance());
        assertThat(account.getCustomerId()).isEqualTo(accountEntity.getCustomerId());
        assertThat(account.getStatus()).isEqualTo(accountEntity.getStatus().getEnumValue());
        assertThat(account.getType()).isEqualTo(accountEntity.getType().getEnumValue());
        assertThat(account.getTransactions().size()).isEqualTo(accountEntity.getTransactions().size());
    }

}
