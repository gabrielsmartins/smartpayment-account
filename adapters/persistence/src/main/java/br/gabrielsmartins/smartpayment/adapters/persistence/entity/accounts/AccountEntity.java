package br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts;


import br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions.FinancialTransactionEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountTypeDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.converter.AccountStatusDataEnumConverter;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.converter.AccountTypeDataEnumConverter;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = {"transactions"})
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Table(name = "tbl_account")
@Entity
@DynamicUpdate(true)
@SelectBeforeUpdate(false)
public class AccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name="account_customer_id")
    private String customerId;

    @Column(name="account_type")
    @Convert(converter = AccountTypeDataEnumConverter.class)
    private AccountTypeDataEnum type;

    @Column(name="account_status")
    @Convert(converter = AccountStatusDataEnumConverter.class)
    private AccountStatusDataEnum status;

    @Column(name="account_balance")
    private BigDecimal balance;

    @Setter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.source", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<FinancialTransactionEntity> transactions = new LinkedList<>();


    public Integer addTransaction(FinancialTransactionEntity transaction){
        FinancialTransactionEntity.FinancialTransactionEntityId transactionId = transaction.getId();
        transactionId.setSource(this);
        this.transactions.add(transaction);
        return this.transactions.size();
    }

    public List<FinancialTransactionEntity> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
