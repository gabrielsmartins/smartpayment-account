package br.gabrielsmartins.smartpayment.application.service.customers;

import br.gabrielsmartins.smartpayment.application.port.in.customers.ValidateCustomerUseCase;
import br.gabrielsmartins.smartpayment.application.port.out.customers.ValidateCustomerPort;
import br.gabrielsmartins.smartpayment.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class ValidateCustomerService implements ValidateCustomerUseCase {

    private final ValidateCustomerPort port;

    @Override
    public boolean isValid(UUID customerId) {
        return port.isValid(customerId);
    }
}
