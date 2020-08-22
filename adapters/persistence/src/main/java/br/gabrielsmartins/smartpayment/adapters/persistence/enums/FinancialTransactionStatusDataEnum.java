package br.gabrielsmartins.smartpayment.adapters.persistence.enums;

import br.gabrielsmartins.smartpayment.application.domain.enums.FinancialTransactionStatusEnum;
import lombok.Getter;

import java.util.stream.Stream;

public enum FinancialTransactionStatusDataEnum {

    COMMITTED(1, FinancialTransactionStatusEnum.COMMITTED),
    UNCOMMITTED(2, FinancialTransactionStatusEnum.UNCOMMITTED),
    ROLLED_BACK(3, FinancialTransactionStatusEnum.ROLLED_BACK);

    @Getter
    private final Integer code;

    @Getter
    private final FinancialTransactionStatusEnum enumValue;

    FinancialTransactionStatusDataEnum(Integer code, FinancialTransactionStatusEnum enumValue){
        this.code = code;
        this.enumValue = enumValue;
    }

    public static FinancialTransactionStatusDataEnum fromCode(Integer code){
        return Stream.of(FinancialTransactionStatusDataEnum.values())
                .filter(status -> status.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    public static FinancialTransactionStatusDataEnum fromEnum(FinancialTransactionStatusEnum enumValue){
        return Stream.of(FinancialTransactionStatusDataEnum.values())
                .filter(status -> status.getEnumValue().equals(enumValue))
                .findFirst()
                .orElse(null);
    }

}
