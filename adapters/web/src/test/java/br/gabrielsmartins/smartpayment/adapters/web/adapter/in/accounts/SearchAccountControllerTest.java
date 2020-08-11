package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.accounts;

import br.gabrielsmartins.smartpayment.adapters.web.mapper.accounts.AccountWebMapperImpl;
import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import br.gabrielsmartins.smartpayment.application.port.in.accounts.SearchAccountUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SearchAccountController.class)
public class SearchAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchAccountUseCase useCase;

    @SpyBean
    private AccountWebMapperImpl mapper;


    @Test
    @DisplayName("Given Account Id When Exists Then Return Account")
    public void givenAccountIdWhenExistsThenReturnAccount() throws Exception {

        Account account = Account.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID().toString())
                .withStatus(AccountStatusEnum.ACTIVE)
                .withType(AccountTypeEnum.FREE)
                .build();


        when(useCase.findById(any(UUID.class))).thenReturn(Optional.ofNullable(account));

        this.mockMvc.perform(get("/v1/accounts/" + account.getId().toString())
                            .header("Accept", MediaType.APPLICATION_JSON))
                     .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Given Customer Id When Exists Then Return Account List")
    public void givenCustomerIdWhenExistsCreateThenReturnCreatedAccountList() throws Exception {

        Account account = Account.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID().toString())
                .withStatus(AccountStatusEnum.ACTIVE)
                .withType(AccountTypeEnum.FREE)
                .build();


        when(useCase.findByCustomerId(anyString())).thenReturn(Collections.singletonList(account));

        this.mockMvc.perform(get("/v1/accounts")
                            .header("Accept", MediaType.APPLICATION_JSON)
                            .param("customer_id", account.getCustomerId().toString()))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Given Account Type When Exists Then Return Account List")
    public void givenAccountTypeWhenExistsCreateThenReturnCreatedAccountList() throws Exception {

        Account account = Account.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID().toString())
                .withStatus(AccountStatusEnum.ACTIVE)
                .withType(AccountTypeEnum.FREE)
                .build();


        when(useCase.findByType(any(AccountTypeEnum.class))).thenReturn(Collections.singletonList(account));

        this.mockMvc.perform(get("/v1/accounts")
                .header("Accept", MediaType.APPLICATION_JSON)
                .param("account_type", account.getType().name()))
                .andExpect(status().isOk());
    }

}
