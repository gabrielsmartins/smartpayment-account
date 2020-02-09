package br.com.smartpayment.config.database;

import br.com.smartpayment.adapters.persistence.mapper.AccountPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceMapperConfigurer {

    @Bean
    public AccountPersistenceMapper accountPersistenceMapper(){
        return new AccountPersistenceMapper();
    }
}
