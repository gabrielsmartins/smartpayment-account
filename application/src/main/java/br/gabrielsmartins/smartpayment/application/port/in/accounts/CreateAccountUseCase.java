package br.gabrielsmartins.smartpayment.application.port.in.accounts;

import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.exception.InvalidCustomerException;

public interface CreateAccountUseCase {

    Account create(Account account) throws InvalidCustomerException;

}
