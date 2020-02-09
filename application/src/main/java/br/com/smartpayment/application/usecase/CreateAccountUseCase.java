package br.com.smartpayment.application.usecase;

import br.com.smartpayment.application.domain.Account;

public interface CreateAccountUseCase {

    Account create(Account account);
}
