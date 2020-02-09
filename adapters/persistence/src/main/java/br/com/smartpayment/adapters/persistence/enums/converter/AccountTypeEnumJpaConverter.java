package br.com.smartpayment.adapters.persistence.enums.converter;

import br.com.smartpayment.adapters.persistence.enums.AccountTypeEnumJpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AccountTypeEnumJpaConverter implements AttributeConverter<AccountTypeEnumJpa,Integer> {

    @Override
    public Integer convertToDatabaseColumn(AccountTypeEnumJpa type) {
        return type.getCode();
    }

    @Override
    public AccountTypeEnumJpa convertToEntityAttribute(Integer code) {
        return AccountTypeEnumJpa.valueOf(code);
    }
}
