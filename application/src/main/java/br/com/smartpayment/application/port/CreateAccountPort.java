package br.com.smartpayment.application.port;

import br.com.smartpayment.application.domain.Account;

public interface CreateAccountPort {
    Account create(Account account);
}
