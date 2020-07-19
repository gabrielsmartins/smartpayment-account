package br.gabrielsmartins.smartpayment.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;

public interface ISaveAccountPersistenceService {

    AccountEntity save(AccountEntity accountEntity);

}
