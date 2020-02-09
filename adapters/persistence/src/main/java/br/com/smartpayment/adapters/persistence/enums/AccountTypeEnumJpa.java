package br.com.smartpayment.adapters.persistence.enums;

public enum AccountTypeEnumJpa {

    FREE(1),
    BASIC(2),
    ENTERPRISE(3);

    private final Integer code;

    private AccountTypeEnumJpa(Integer code){
        this.code = code;
    }

    public static AccountTypeEnumJpa valueOf(Integer code) {
        for(AccountTypeEnumJpa type : AccountTypeEnumJpa.values()){
            if(type.getCode().equals(code))
                return type;
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }
}
