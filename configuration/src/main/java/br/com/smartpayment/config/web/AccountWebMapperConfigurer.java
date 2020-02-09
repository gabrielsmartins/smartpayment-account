package br.com.smartpayment.config.web;

import br.com.smartpayment.adapters.web.mapper.AccountWebMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountWebMapperConfigurer {

    @Bean
    public AccountWebMapper webMapper(){
       return new AccountWebMapper();
    }
}
