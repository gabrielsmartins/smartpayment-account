package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.advice;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.accounts.CreateAccountController;
import br.gabrielsmartins.smartpayment.adapters.web.dto.accounts.AccountDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.accounts.AccountWebMapperImpl;
import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import br.gabrielsmartins.smartpayment.application.exception.InvalidCustomerException;
import br.gabrielsmartins.smartpayment.application.port.in.accounts.CreateAccountUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreateAccountController.class)
public class ExceptionHandlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateAccountUseCase useCase;

    @SpyBean
    private AccountWebMapperImpl mapper;

    private ObjectMapper objectMapper;



    @BeforeEach
    public void setup(){
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }


    @Test
    @DisplayName("Given Account When Customer Id Is Invalid Then Throw Exception")
    public void givenAccountWhenCustomerIdIsInvalidThenThrowException() throws Exception {

        AccountDTO accountDTO = AccountDTO.builder()
                .withId(UUID.randomUUID())
                .withBalance(new BigDecimal(1500))
                .withCustomerId(UUID.randomUUID())
                .withStatus(AccountStatusEnum.ACTIVE.name())
                .withType(AccountTypeEnum.FREE.name())
                .build();


        String content = this.objectMapper.writeValueAsString(accountDTO);

        when(useCase.create(any(Account.class))).thenThrow(new InvalidCustomerException("Invalid Customer ID"));

        this.mockMvc.perform(post("/v1/accounts")
                .content(content)
                .header("Accept", MediaType.APPLICATION_JSON)
                .header("Content-type", MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

}
