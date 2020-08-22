package br.gabrielsmartins.smartpayment.adapters.web.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "br.gabrielsmartins.smartpayment.adapters.web.adapter.out.*")
@Configuration
public class FeignConfiguration {
}
