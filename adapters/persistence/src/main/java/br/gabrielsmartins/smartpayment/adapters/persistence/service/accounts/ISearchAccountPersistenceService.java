package br.gabrielsmartins.smartpayment.adapters.persistence.service.accounts;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountTypeDataEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISearchAccountPersistenceService {

    Optional<AccountEntity> findById(UUID id);

    List<AccountEntity> findByCustomerId(UUID customerId);

    List<AccountEntity> findByType(AccountTypeDataEnum type);

}
