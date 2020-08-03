package br.gabrielsmartins.smartpayment.adapters.persistence.service.accounts;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountTypeDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.accounts.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchAccountPersistenceService implements ISearchAccountPersistenceService {

    private final AccountRepository repository;

    @Override
    public Optional<AccountEntity> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<AccountEntity> findByCustomerId(UUID customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public List<AccountEntity> findByType(AccountTypeDataEnum type) {
        return repository.findByType(type);
    }
}
