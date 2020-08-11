package br.gabrielsmartins.smartpayment.application.port.out.customers;

public interface ValidateCustomerPort {

    boolean isValid(String customerId);

}
