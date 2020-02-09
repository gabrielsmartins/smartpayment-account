package br.com.smartpayment.application.domain;

import br.com.smartpayment.application.enums.AccountTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

    private Long id;
    private AccountTypeEnum type;

}
