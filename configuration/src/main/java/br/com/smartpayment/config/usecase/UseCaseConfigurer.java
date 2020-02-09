package br.com.smartpayment.config.usecase;

import br.com.smartpayment.application.port.CreateAccountPort;
import br.com.smartpayment.application.service.CreateAccountService;
import br.com.smartpayment.application.usecase.CreateAccountUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfigurer {

    @Bean
    public CreateAccountUseCase createAccountUseCase(CreateAccountPort accountPort){
        return new CreateAccountService(accountPort);
    }
}
