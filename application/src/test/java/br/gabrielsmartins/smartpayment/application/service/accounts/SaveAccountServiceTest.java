package br.gabrielsmartins.smartpayment.application.service.accounts;

import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.FinancialTransactionStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import br.gabrielsmartins.smartpayment.application.port.out.accounts.SaveAccountPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveAccountServiceTest {

    private SaveAccountService service;
    private SaveAccountPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(SaveAccountPort.class);
        this.service = new SaveAccountService(port);
    }

    @Test
    @DisplayName("Given Account When Save Then Return Saved Account")
    public void givenAccountWhenSaveThenReturnSavedAccount(){

        when(port.save(any(Account.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

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
                                 .withTransactions(Arrays.asList(transaction))
                                 .build();

        Account savedAccount = this.service.save(account);
        assertThat(savedAccount).isNotNull();
    }


}
