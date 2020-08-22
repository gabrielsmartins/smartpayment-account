package br.gabrielsmartins.smartpayment.application.port.in.customers;



public interface ValidateCustomerUseCase {

    boolean isValid(String customerId);

}
