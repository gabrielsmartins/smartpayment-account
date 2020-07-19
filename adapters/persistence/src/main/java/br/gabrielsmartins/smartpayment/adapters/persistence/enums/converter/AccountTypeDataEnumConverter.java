package br.gabrielsmartins.smartpayment.adapters.persistence.enums.converter;

import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountTypeDataEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AccountTypeDataEnumConverter implements AttributeConverter<AccountTypeDataEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(AccountTypeDataEnum type) {
        return type.getCode();
    }

    @Override
    public AccountTypeDataEnum convertToEntityAttribute(Integer code) {
        return AccountTypeDataEnum.fromCode(code);
    }
}
