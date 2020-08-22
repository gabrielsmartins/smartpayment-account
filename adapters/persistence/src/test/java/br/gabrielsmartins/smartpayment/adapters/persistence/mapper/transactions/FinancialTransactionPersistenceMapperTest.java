package br.gabrielsmartins.smartpayment.adapters.persistence.mapper.transactions;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions.FinancialTransactionEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.FinancialTransactionStatusDataEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.FinancialTransactionStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class FinancialTransactionPersistenceMapperTest {

    private FinancialTransactionPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new FinancialTransactionPersistenceMapperImpl();
    }

    @Test
    @DisplayName("Given Financial Transaction Domain When Map Then Return Financial Transaction Entity")
    public void givenFinancialTransactionDomainWhenMapThenReturnFinancialTransactionEntity(){
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

        FinancialTransactionEntity transactionEntity = this.mapper.mapToEntity(transaction);

        assertThat(transactionEntity.getId().getIdentifier()).isEqualTo(transaction.getId().getIdentifier());
        assertThat(transactionEntity.getAccountBalance()).isEqualTo(transaction.getAccountBalance());
        assertThat(transactionEntity.getAmount()).isEqualTo(transaction.getAmount());
        assertThat(transactionEntity.getDescription()).isEqualTo(transaction.getDescription());
        assertThat(transactionEntity.getCreatedAt()).isEqualTo(transaction.getCreatedAt());
        assertThat(transactionEntity.getStatus().getEnumValue()).isEqualTo(transaction.getStatus());
    }

    @Test
    @DisplayName("Given Financial Transaction Entity When Map Then Return Financial Transaction Domain")
    public void givenFinancialTransactionEntityWhenMapThenReturnFinancialTransactionDomain(){
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

        FinancialTransaction transaction = this.mapper.mapToDomain(transactionEntity);

        assertThat(transaction.getId().getIdentifier()).isEqualTo(transactionEntity.getId().getIdentifier());
        assertThat(transaction.getAccountBalance()).isEqualTo(transactionEntity.getAccountBalance());
        assertThat(transaction.getAmount()).isEqualTo(transactionEntity.getAmount());
        assertThat(transaction.getDescription()).isEqualTo(transactionEntity.getDescription());
        assertThat(transaction.getCreatedAt()).isEqualTo(transactionEntity.getCreatedAt());
        assertThat(transaction.getStatus()).isEqualTo(transactionEntity.getStatus().getEnumValue());
    }
}
