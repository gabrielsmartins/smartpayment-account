package br.com.smartpayment.application.service;

import br.com.smartpayment.application.domain.Account;
import br.com.smartpayment.application.port.CreateAccountPort;
import br.com.smartpayment.application.usecase.CreateAccountUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {

    private CreateAccountPort accountPort;

    public Account create(Account account){
        return this.accountPort.create(account);
    }
}
