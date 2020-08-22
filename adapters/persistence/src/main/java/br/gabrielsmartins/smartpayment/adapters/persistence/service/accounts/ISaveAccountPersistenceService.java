package br.gabrielsmartins.smartpayment.adapters.persistence.service.accounts;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;

public interface ISaveAccountPersistenceService {

    AccountEntity save(AccountEntity accountEntity);

}
