package br.gabrielsmartins.smartpayment.application.port.in.accounts;

import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SearchAccountUseCase {

    Optional<Account> findById(UUID id);

    List<Account> findByCustomerId(String customerId);

    List<Account> findByType(AccountTypeEnum type);

}
