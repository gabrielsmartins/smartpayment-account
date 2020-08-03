package br.gabrielsmartins.smartpayment.adapters.web.adapter.out.customers;

import br.gabrielsmartins.smartpayment.application.port.out.customers.ValidateCustomerPort;
import br.gabrielsmartins.smartpayment.common.stereotype.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@WebAdapter
@RequiredArgsConstructor
public class ValidateCustomerWebAdapter implements ValidateCustomerPort {

    private final CustomerWebService webService;

    @Override
    public boolean isValid(UUID customerId) {
        ResponseEntity<CustomerDTO> response = webService.findById(customerId.toString());
        return response.getStatusCode() == HttpStatus.OK;
    }

}
