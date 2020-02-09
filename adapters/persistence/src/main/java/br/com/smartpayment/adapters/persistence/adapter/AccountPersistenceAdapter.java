package br.com.smartpayment.adapters.persistence.adapter;

import br.com.smartpayment.adapters.persistence.entity.AccountEntity;
import br.com.smartpayment.adapters.persistence.mapper.AccountPersistenceMapper;
import br.com.smartpayment.adapters.persistence.repository.AccountRepository;
import br.com.smartpayment.application.domain.Account;
import br.com.smartpayment.application.port.CreateAccountPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountPersistenceAdapter implements CreateAccountPort {

    private final AccountRepository repository;
    private final AccountPersistenceMapper mapper;


    @Override
    public Account create(Account account) {
        AccountEntity accountEntity = mapper.mapToEntity(account);
        AccountEntity createdAccount = repository.saveAndFlush(accountEntity);
        return mapper.mapToDomain(createdAccount);
    }
}
