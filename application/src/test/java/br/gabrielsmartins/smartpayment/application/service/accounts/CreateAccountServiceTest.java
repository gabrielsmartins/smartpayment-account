package br.gabrielsmartins.smartpayment.application.service.accounts;

import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.FinancialTransactionStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import br.gabrielsmartins.smartpayment.application.exception.InvalidCustomerException;
import br.gabrielsmartins.smartpayment.application.port.in.accounts.SaveAccountUseCase;
import br.gabrielsmartins.smartpayment.application.port.in.customers.ValidateCustomerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateAccountServiceTest {

    private CreateAccountService service;
    private SaveAccountUseCase saveAccountUseCase;
    private ValidateCustomerUseCase validateCustomerUseCase;

    @BeforeEach
    public void setup(){
        this.saveAccountUseCase = mock(SaveAccountUseCase.class);
        this.validateCustomerUseCase = mock(ValidateCustomerUseCase.class);
        this.service = new CreateAccountService(saveAccountUseCase, validateCustomerUseCase);
    }

    @Test
    @DisplayName("Given Account When Customer Id Is Valid Then Return Created Account")
    public void givenAccountWhenCustomerIdIsValidThenReturnSavedAccount() throws InvalidCustomerException {



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

        when(saveAccountUseCase.save(any(Account.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(validateCustomerUseCase.isValid(any(UUID.class))).thenReturn(true);

        Account savedAccount = this.service.create(account);
        assertThat(savedAccount).isNotNull();
    }

    @Test
    @DisplayName("Given Account When Is Invalid Then Throw Exception")
    public void givenAccountWhenCustomerIdIsInvalidThenThrowException(){



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

        when(saveAccountUseCase.save(any(Account.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(validateCustomerUseCase.isValid(any(UUID.class))).thenReturn(false);

       assertThrows(InvalidCustomerException.class, ()-> {
           this.service.create(account);
       });
    }


}
