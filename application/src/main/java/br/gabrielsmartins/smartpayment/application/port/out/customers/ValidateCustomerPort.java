package br.gabrielsmartins.smartpayment.application.port.out.customers;

import java.util.UUID;

public interface ValidateCustomerPort {

    boolean isValid(UUID customerId);

}
