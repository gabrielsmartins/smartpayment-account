package br.gabrielsmartins.smartpayment.application.service.customers;

import br.gabrielsmartins.smartpayment.application.port.out.customers.ValidateCustomerPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateCustomerServiceTest {

    private ValidateCustomerService service;
    private ValidateCustomerPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(ValidateCustomerPort.class);
        this.service = new ValidateCustomerService(port);
    }

    @Test
    @DisplayName("Given Customer Id When Valid Then Return True")
    public void givenCustomerIdWhenValidThenReturnTrue(){
        String customerId = UUID.randomUUID().toString();

        when(port.isValid(anyString())).thenReturn(true);

        boolean isValid = service.isValid(customerId);
        assertThat(isValid).isTrue();
    }
}
