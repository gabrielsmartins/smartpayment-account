package br.gabrielsmartins.smartpayment.adapters.persistence.enums.converter;

import br.gabrielsmartins.smartpayment.adapters.persistence.enums.FinancialTransactionStatusDataEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FinancialTransactionStatusDataEnumConverter implements AttributeConverter<FinancialTransactionStatusDataEnum,Integer> {


    @Override
    public Integer convertToDatabaseColumn(FinancialTransactionStatusDataEnum financialTransactionStatusDataEnum) {
        return financialTransactionStatusDataEnum.getCode();
    }

    @Override
    public FinancialTransactionStatusDataEnum convertToEntityAttribute(Integer code) {
        return FinancialTransactionStatusDataEnum.fromCode(code);
    }
}
