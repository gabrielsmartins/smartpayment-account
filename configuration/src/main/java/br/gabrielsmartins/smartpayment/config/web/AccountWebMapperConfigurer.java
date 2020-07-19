package br.gabrielsmartins.smartpayment.config.web;

import br.gabrielsmartins.smartpayment.adapters.web.mapper.AccountWebMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountWebMapperConfigurer {

    @Bean
    public AccountWebMapper accountWebMapper(){
       return new AccountWebMapper();
    }
}
