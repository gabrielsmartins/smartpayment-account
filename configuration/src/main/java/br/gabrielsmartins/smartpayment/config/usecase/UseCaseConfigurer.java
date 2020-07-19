package br.gabrielsmartins.smartpayment.config.usecase;

import br.gabrielsmartins.smartpayment.application.port.out.accounts.SaveAccountPort;
import br.gabrielsmartins.smartpayment.application.service.accounts.SaveAccountService;
import br.gabrielsmartins.smartpayment.application.port.in.accounts.SaveAccountUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfigurer {

    @Bean
    public SaveAccountUseCase createAccountUseCase(SaveAccountPort accountPort){
        return new SaveAccountService(accountPort);
    }
}
