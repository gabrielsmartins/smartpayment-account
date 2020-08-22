package br.gabrielsmartins.smartpayment.adapters.persistence.adapter.accounts;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountTypeDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.accounts.AccountPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.accounts.SearchAccountPersistenceService;
import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import br.gabrielsmartins.smartpayment.application.port.out.accounts.SearchAccountPort;
import br.gabrielsmartins.smartpayment.common.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class SearchAccountPersistenceAdapter implements SearchAccountPort {

    private final SearchAccountPersistenceService service;
    private final AccountPersistenceMapper mapper;

    @Override
    public Optional<Account> findById(UUID id) {
        Optional<AccountEntity> optionalAccountEntity = service.findById(id);
        if(optionalAccountEntity.isPresent())
            return Optional.ofNullable(mapper.mapToDomain(optionalAccountEntity.get()));
        return Optional.empty();
    }

    @Override
    public List<Account> findByCustomerId(String customerId) {
        List<AccountEntity> accountEntities = service.findByCustomerId(customerId);
        return mapper.mapToDomain(accountEntities);
    }

    @Override
    public List<Account> findByType(AccountTypeEnum accountTypeEnum) {
        List<AccountEntity> accountEntities = service.findByType(AccountTypeDataEnum.fromEnum(accountTypeEnum));
        return mapper.mapToDomain(accountEntities);
    }
}
