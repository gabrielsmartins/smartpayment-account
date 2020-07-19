package br.gabrielsmartins.smartpayment.application.service.accounts;

import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.port.in.accounts.SaveAccountUseCase;
import br.gabrielsmartins.smartpayment.application.port.out.accounts.SaveAccountPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveAccountService implements SaveAccountUseCase {

    private final SaveAccountPort port;

    public Account save(Account account){
        return this.port.save(account);
    }
}
