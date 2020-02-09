package br.com.smartpayment.adapters.web.service;

import br.com.smartpayment.application.domain.Account;
import br.com.smartpayment.application.usecase.CreateAccountUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private final CreateAccountUseCase createAccountUseCase;

    public Account create(Account account) {
        return createAccountUseCase.create(account);
    }
}
