package br.gabrielsmartins.smartpayment.config.database;

import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.accounts.AccountPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceMapperConfigurer {

    @Bean
    public AccountPersistenceMapper accountPersistenceMapper(){
        return new AccountPersistenceMapper();
    }
}
