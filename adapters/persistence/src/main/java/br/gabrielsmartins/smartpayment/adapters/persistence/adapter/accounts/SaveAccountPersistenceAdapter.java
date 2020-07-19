package br.gabrielsmartins.smartpayment.adapters.persistence.adapter.accounts;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.accounts.AccountPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.SaveAccountPersistenceService;
import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.port.out.accounts.SaveAccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveAccountPersistenceAdapter implements SaveAccountPort {

    private final SaveAccountPersistenceService service;
    private final AccountPersistenceMapper mapper;


    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = mapper.mapToEntity(account);
        AccountEntity savedAccount = service.save(accountEntity);
        return mapper.mapToDomain(savedAccount);
    }
}
