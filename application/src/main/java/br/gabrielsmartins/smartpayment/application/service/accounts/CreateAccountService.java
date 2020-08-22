package br.gabrielsmartins.smartpayment.application.service.accounts;

import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.exception.InvalidCustomerException;
import br.gabrielsmartins.smartpayment.application.port.in.accounts.CreateAccountUseCase;
import br.gabrielsmartins.smartpayment.application.port.in.accounts.SaveAccountUseCase;
import br.gabrielsmartins.smartpayment.application.port.in.customers.ValidateCustomerUseCase;
import br.gabrielsmartins.smartpayment.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {

    private final SaveAccountUseCase saveAccountUseCase;
    private final ValidateCustomerUseCase validateCustomerUseCase;

    @Override
    public Account create(Account account) throws InvalidCustomerException {
        if(validateCustomerUseCase.isValid(account.getCustomerId()))
            return saveAccountUseCase.save(account);
        throw new InvalidCustomerException("Invalid Customer ID");
    }

}
