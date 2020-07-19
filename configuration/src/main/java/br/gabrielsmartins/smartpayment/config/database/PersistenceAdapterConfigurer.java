package br.gabrielsmartins.smartpayment.config.database;

import br.gabrielsmartins.smartpayment.adapters.persistence.adapter.accounts.SaveAccountPersistenceAdapter;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.accounts.AccountPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceAdapterConfigurer {

    @Bean
    public SaveAccountPersistenceAdapter accountPersistenceAdapter(AccountRepository repository, AccountPersistenceMapper mapper){
        return new SaveAccountPersistenceAdapter(repository,mapper);
    }
}
