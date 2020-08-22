package br.gabrielsmartins.smartpayment.adapters.web.mapper.transactions;

import br.gabrielsmartins.smartpayment.adapters.web.dto.transactions.FinancialTransactionDTO;
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

public class FinancialTransactionWebMapperTest {

    private FinancialTransactionWebMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new FinancialTransactionWebMapperImpl();
    }

    @Test
    @DisplayName("Given Financial Transaction Domain When Map Then Return Financial Transaction DTO")
    public void givenFinancialTransactionDomainWhenMapThenReturnFinancialTransactionDTO(){

        Account accountTarget = Account.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID().toString())
                .withStatus(AccountStatusEnum.ACTIVE)
                .withType(AccountTypeEnum.FREE)
                .build();

        FinancialTransaction financialTransaction = FinancialTransaction.builder()
                .withId(FinancialTransaction.FinancialTransactionId.builder()
                        .withIdentifier(UUID.randomUUID())
                        .build())
                .withAccountBalance(new BigDecimal(1500))
                .withAmount(new BigDecimal(100))
                .withDescription("Foo")
                .withCreatedAt(LocalDateTime.now())
                .withStatus(FinancialTransactionStatusEnum.COMMITTED)
                .withTarget(accountTarget)
                .build();

        Account account = Account.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID().toString())
                .withStatus(AccountStatusEnum.ACTIVE)
                .withType(AccountTypeEnum.FREE)
                .build();

        account.addTransaction(financialTransaction);

        FinancialTransactionDTO financialTransactionDTO = this.mapper.mapToDTO(financialTransaction);

        assertThat(financialTransactionDTO.getAccountId()).isEqualTo(account.getId());
        assertThat(financialTransactionDTO.getIdentifier()).isEqualTo(financialTransaction.getId().getIdentifier());
        assertThat(financialTransactionDTO.getCreatedAt()).isEqualTo(financialTransaction.getCreatedAt());
        assertThat(financialTransactionDTO.getDescription()).isEqualTo(financialTransaction.getDescription());
        assertThat(financialTransactionDTO.getAmount()).isEqualTo(financialTransaction.getAmount());
        assertThat(financialTransactionDTO.getAccountBalance()).isEqualTo(financialTransaction.getAccountBalance());
        assertThat(financialTransactionDTO.getStatus()).isEqualTo(financialTransaction.getStatus().name());
        assertThat(financialTransactionDTO.getAccountTargetId()).isEqualTo(financialTransaction.getTarget().getId());
    }

}
