package br.com.smartpayment.adapters.persistence.entity;


import br.com.smartpayment.adapters.persistence.enums.AccountTypeEnumJpa;
import br.com.smartpayment.adapters.persistence.enums.converter.AccountTypeEnumJpaConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "account")
public class AccountEntity {

    @Column(name = "id")
    private Long id;

    @Column(name="type")
    @Convert(converter = AccountTypeEnumJpaConverter.class)
    private AccountTypeEnumJpa type;
}
