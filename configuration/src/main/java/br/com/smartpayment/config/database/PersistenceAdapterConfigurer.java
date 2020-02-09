package br.com.smartpayment.config.database;

import br.com.smartpayment.adapters.persistence.adapter.AccountPersistenceAdapter;
import br.com.smartpayment.adapters.persistence.mapper.AccountPersistenceMapper;
import br.com.smartpayment.adapters.persistence.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceAdapterConfigurer {

    @Bean
    public AccountPersistenceAdapter accountPersistenceAdapter(AccountRepository repository, AccountPersistenceMapper mapper){
        return new AccountPersistenceAdapter(repository,mapper);
    }
}
