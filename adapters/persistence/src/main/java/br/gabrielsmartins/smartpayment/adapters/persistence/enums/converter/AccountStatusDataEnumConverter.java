package br.gabrielsmartins.smartpayment.adapters.persistence.enums.converter;

import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountStatusDataEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AccountStatusDataEnumConverter implements AttributeConverter<AccountStatusDataEnum,String> {


    @Override
    public String convertToDatabaseColumn(AccountStatusDataEnum accountStatusDataEnum) {
        return accountStatusDataEnum.getCode();
    }

    @Override
    public AccountStatusDataEnum convertToEntityAttribute(String code) {
        return AccountStatusDataEnum.fromCode(code);
    }
}
