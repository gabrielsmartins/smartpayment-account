package br.com.smartpayment.adapters.web.mapper;

import br.com.smartpayment.adapters.web.dto.AccountRequestDto;
import br.com.smartpayment.adapters.web.dto.AccountResponseDto;
import br.com.smartpayment.application.domain.Account;
import br.com.smartpayment.application.enums.AccountTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class AccountWebMapper {

    public Account mapToDomain(AccountRequestDto accountRequestDto) {
        Account account = new Account();
        account.setType(AccountTypeEnum.valueOf(accountRequestDto.getType()));
        return account;
    }

    public AccountResponseDto mapToDto(Account account) {
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setId(account.getId());
        accountResponseDto.setType(account.getType().name());
        return  accountResponseDto;
    }
}
