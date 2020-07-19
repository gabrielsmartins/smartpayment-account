package br.gabrielsmartins.smartpayment.adapters.persistence.enums;

import br.gabrielsmartins.smartpayment.application.domain.enums.AccountStatusEnum;
import lombok.Getter;

import java.util.stream.Stream;

public enum AccountStatusDataEnum {

    ACTIVE("A", AccountStatusEnum.ACTIVE),
    INACTIVE("I", AccountStatusEnum.INACTIVE),
    BLOCKED("B", AccountStatusEnum.BLOCKED);


    @Getter
    private final String code;

    @Getter
    private final AccountStatusEnum enumValue;

    AccountStatusDataEnum(String code, AccountStatusEnum enumValue){
        this.code = code;
        this.enumValue = enumValue;
    }

    public static AccountStatusDataEnum fromCode(String code){
        return Stream.of(AccountStatusDataEnum.values())
                .filter(status -> status.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }

    public static AccountStatusDataEnum fromEnum(AccountStatusEnum accountStatusEnum){
        return Stream.of(AccountStatusDataEnum.values())
                .filter(status -> status.getEnumValue().equals(accountStatusEnum))
                .findFirst()
                .orElse(null);
    }



}
