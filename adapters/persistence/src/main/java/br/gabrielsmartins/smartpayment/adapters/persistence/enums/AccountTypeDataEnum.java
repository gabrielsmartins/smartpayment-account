package br.gabrielsmartins.smartpayment.adapters.persistence.enums;

import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import lombok.Getter;

import java.util.stream.Stream;

public enum AccountTypeDataEnum {

    FREE(1, AccountTypeEnum.FREE),
    BASIC(2, AccountTypeEnum.LINKED),
    ENTERPRISE(3, AccountTypeEnum.PAYMENT);

    @Getter
    private final Integer code;

    @Getter
    private final AccountTypeEnum enumValue;

    AccountTypeDataEnum(Integer code, AccountTypeEnum enumValue){
        this.code = code;
        this.enumValue = enumValue;
    }

    public static AccountTypeDataEnum fromCode(Integer code) {
        return Stream.of(AccountTypeDataEnum.values())
                .filter(type -> type.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    public static AccountTypeDataEnum fromEnum(AccountTypeEnum accountTypeEnum) {
        return Stream.of(AccountTypeDataEnum.values())
                .filter(type -> type.getEnumValue().equals(accountTypeEnum))
                .findFirst()
                .orElse(null);
    }


}
