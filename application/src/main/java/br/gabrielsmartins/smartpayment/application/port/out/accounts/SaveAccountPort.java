package br.gabrielsmartins.smartpayment.application.port.out.accounts;

import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;

public interface SaveAccountPort {
    Account save(Account account);
}
