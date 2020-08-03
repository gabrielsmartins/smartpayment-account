package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.transactions;

import br.gabrielsmartins.smartpayment.adapters.web.mapper.transactions.FinancialTransactionWebMapperImpl;
import br.gabrielsmartins.smartpayment.application.domain.enums.FinancialTransactionStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import br.gabrielsmartins.smartpayment.application.port.in.transactions.SearchFinancialTransactionUseCase;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SearchFinancialTransactionController.class)
public class SearchFinancialTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchFinancialTransactionUseCase useCase;

    @SpyBean
    private FinancialTransactionWebMapperImpl mapper;

    @Test
    @DisplayName("Given Account Id And Interval When Exists Then Return Financial Transaction List")
    public void givenAccountIdAndIntervalWhenExistsThenReturnFinancialTransactionList() throws Exception {

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

        when(useCase.findByAccountIdAndInterval(any(UUID.class), any(LocalDateTime.class), any(LocalDateTime.class)))
                                              .thenReturn(Collections.singletonList(transaction));

        this.mockMvc.perform(get("/v1/financial_transactions")
                            .header("Accept", MediaType.APPLICATION_JSON)
                            .param("account_id", UUID.randomUUID().toString())
                            .param("start_date_time", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                            .param("end_date_time", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Given Customer Id And Interval When Exists Then Return Financial Transaction List")
    public void givenCustomerIdAndIntervalWhenExistsThenReturnFinancialTransactionList() throws Exception {

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

        when(useCase.findByCustomerIdAndInterval(any(UUID.class), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(transaction));

        this.mockMvc.perform(get("/v1/financial_transactions")
                .header("Accept", MediaType.APPLICATION_JSON)
                .param("customer_id", UUID.randomUUID().toString())
                .param("start_date_time", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .param("end_date_time", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(status().isOk());
    }

}
