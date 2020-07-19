package br.gabrielsmartins.smartpayment.adapters.web.mapper;

import br.gabrielsmartins.smartpayment.adapters.web.dto.AccountDTO;
import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE,
builder = @Builder(disableBuilder = true))
public interface AccountWebMapper {

    Account mapToDomain(AccountDTO accountRequestDto);

    AccountDTO mapToDto(Account account);

}
