package br.gabrielsmartins.smartpayment.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveAccountPersistenceService implements ISaveAccountPersistenceService{

    private final AccountRepository repository;

    @Override
    public AccountEntity save(AccountEntity accountEntity) {
        return repository.save(accountEntity);
    }
}
