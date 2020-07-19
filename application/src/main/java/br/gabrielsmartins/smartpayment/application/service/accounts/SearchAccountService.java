package br.gabrielsmartins.smartpayment.application.service.accounts;

import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import br.gabrielsmartins.smartpayment.application.port.in.accounts.SearchAccountUseCase;
import br.gabrielsmartins.smartpayment.application.port.out.accounts.SearchAccountPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class SearchAccountService implements SearchAccountUseCase {

    private final SearchAccountPort port;

    @Override
    public Optional<Account> findById(UUID id) {
        return port.findById(id);
    }

    @Override
    public Optional<Account> findByCustomerId(UUID customerId) {
        return port.findByCustomerId(customerId);
    }

    @Override
    public List<Account> findByType(AccountTypeEnum type) {
        return port.findByType(type);
    }
}
