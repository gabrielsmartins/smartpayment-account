package br.gabrielsmartins.smartpayment.application.port.in.accounts;

import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;

public interface SaveAccountUseCase {

    Account save(Account account);
}
