package br.gabrielsmartins.smartpayment.adapters.web.mapper.accounts;

import br.gabrielsmartins.smartpayment.adapters.web.dto.accounts.AccountDTO;
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

public class AccountWebMapperTest {

    private AccountWebMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new AccountWebMapperImpl();
    }

    @Test
    @DisplayName("Given Account Domain When Map Then Return Account DTO")
    public void givenAccountDomainWhenMapThenReturnAccountDTO(){

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

        AccountDTO accountDTO = mapper.mapToDTO(account);

        assertThat(accountDTO.getId()).isEqualTo(account.getId());
        assertThat(accountDTO.getCustomerId()).isEqualTo(account.getCustomerId());
        assertThat(accountDTO.getType()).isEqualTo(account.getType().name());
        assertThat(accountDTO.getStatus()).isEqualTo(account.getStatus().name());
        assertThat(accountDTO.getBalance()).isEqualTo(account.getBalance());
    }


    @Test
    @DisplayName("Given Account DTO When Map Then Return Account Domain")
    public void givenAccountDTOWhenMapThenReturnAccountDomain(){

        AccountDTO accountDTO = AccountDTO.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID())
                .withStatus(AccountStatusEnum.ACTIVE.name())
                .withType(AccountTypeEnum.FREE.name())
                .build();

        Account account = mapper.mapToDomain(accountDTO);



        assertThat(account.getId()).isEqualTo(accountDTO.getId());
        assertThat(account.getCustomerId()).isEqualTo(accountDTO.getCustomerId());
        assertThat(account.getType().name()).isEqualTo(accountDTO.getType());
        assertThat(account.getStatus().name()).isEqualTo(accountDTO.getStatus());
        assertThat(account.getBalance()).isEqualTo(accountDTO.getBalance());
    }
}
