package br.gabrielsmartins.smartpayment.adapters.web.adapter.out.customers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateCustomerWebAdapterTest {

    private ValidateCustomerWebAdapter adapter;
    private CustomerWebService webService;

    @BeforeEach
    public void setup(){
        this.webService = mock(CustomerWebService.class);
        this.adapter = new ValidateCustomerWebAdapter(webService);
    }

    @Test
    @DisplayName("Given Customer Id When Is Valid Then Return True")
    public void givenCustomerIdWhenIsValidThenReturnTrue(){

        when(webService.findById(anyString())).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        boolean isValid = this.adapter.isValid(UUID.randomUUID().toString());
        assertThat(isValid).isTrue();
    }
}
