package br.com.smartpayment.adapters.persistence.mapper;

import br.com.smartpayment.adapters.persistence.entity.AccountEntity;
import br.com.smartpayment.adapters.persistence.enums.AccountTypeEnumJpa;
import br.com.smartpayment.application.domain.Account;
import br.com.smartpayment.application.enums.AccountTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class AccountPersistenceMapper {

    public AccountEntity mapToEntity(Account account){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(account.getId());
        accountEntity.setType(AccountTypeEnumJpa.valueOf(account.toString()));
        return accountEntity;
    }

    public Account mapToDomain(AccountEntity accountEntity){
        Account account = new Account();
        account.setId(accountEntity.getId());
        account.setType(AccountTypeEnum.valueOf(accountEntity.getType().name()));
        return account;
    }

}
