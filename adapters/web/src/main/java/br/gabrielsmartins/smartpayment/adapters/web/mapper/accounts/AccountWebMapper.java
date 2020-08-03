package br.gabrielsmartins.smartpayment.adapters.web.mapper.accounts;

import br.gabrielsmartins.smartpayment.adapters.web.dto.accounts.AccountDTO;
import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE,
builder = @Builder(disableBuilder = true))
public interface AccountWebMapper {


    Account mapToDomain(AccountDTO accountRequestDto);

    List<Account> mapToDomain(List<AccountDTO> accountDTOS);

    AccountDTO mapToDTO(Account account);

    List<AccountDTO> mapToDTO(List<Account> accounts);

}
