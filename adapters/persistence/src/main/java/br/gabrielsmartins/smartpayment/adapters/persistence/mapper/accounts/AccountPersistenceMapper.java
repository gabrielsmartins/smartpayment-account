package br.gabrielsmartins.smartpayment.adapters.persistence.mapper.accounts;


import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountTypeDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.transactions.FinancialTransactionPersistenceMapper;
import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
injectionStrategy = InjectionStrategy.CONSTRUCTOR,
uses = {FinancialTransactionPersistenceMapper.class},
unmappedSourcePolicy = ReportingPolicy.IGNORE,
unmappedTargetPolicy = ReportingPolicy.IGNORE, collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
builder = @Builder(disableBuilder = true))
public interface AccountPersistenceMapper {

    @Mappings({
            @Mapping(source = "type", target = "type", qualifiedByName = "toType"),
            @Mapping(source = "status", target = "status", qualifiedByName = "toType")
    })
    AccountEntity mapToEntity(Account account);

    @Mappings({
            @Mapping(source = "type", target = "type", qualifiedByName = "fromType"),
            @Mapping(source = "status", target = "status", qualifiedByName = "fromType")
    })
    Account mapToDomain(AccountEntity accountEntity);

    List<AccountEntity> mapToEntity(List<Account> account);

    List<Account> mapToDomain(List<AccountEntity> accountEntity);

    default AccountTypeDataEnum toType(AccountTypeEnum accountTypeEnum){
        return AccountTypeDataEnum.fromEnum(accountTypeEnum);
    }

    default AccountStatusDataEnum toType(AccountStatusEnum accountStatusEnum){
        return AccountStatusDataEnum.fromEnum(accountStatusEnum);
    }

    default AccountTypeEnum fromType(AccountTypeDataEnum accountTypeDataEnum){
        return accountTypeDataEnum.getEnumValue();
    }

    default AccountStatusEnum fromType(AccountStatusDataEnum accountStatusDataEnum){
        return accountStatusDataEnum.getEnumValue();
    }


}
