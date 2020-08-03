package br.gabrielsmartins.smartpayment.application.port.in.customers;

import java.util.UUID;

public interface ValidateCustomerUseCase {

    boolean isValid(UUID customerId);

}
