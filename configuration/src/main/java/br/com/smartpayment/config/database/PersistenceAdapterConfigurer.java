package br.com.smartpayment.config.database;

import br.com.smartpayment.adapters.persistence.adapter.AccountPersistenceAdapter;
import br.com.smartpayment.adapters.persistence.mapper.AccountPersistenceMapper;
import br.com.smartpayment.adapters.persistence.repository.AccountRepository;
import br.com.smartpayment.application.port.CreateAccountPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PersistenceAdapterConfigurer {

    @Bean
    @Primary
    public CreateAccountPort persistenceAdapter(AccountRepository repository, AccountPersistenceMapper mapper){
        return new AccountPersistenceAdapter(repository,mapper);
    }
}
