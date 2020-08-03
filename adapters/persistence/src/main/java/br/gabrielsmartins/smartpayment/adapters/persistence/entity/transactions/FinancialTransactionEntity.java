package br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.FinancialTransactionStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.converter.FinancialTransactionStatusDataEnumConverter;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(setterPrefix = "with")
@Getter
@Setter
@Table(name = "financial_transaction")
@Entity
@DynamicUpdate(true)
@SelectBeforeUpdate(false)
public class FinancialTransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private FinancialTransactionEntityId id;

    @Column(name = "datetime")
    private LocalDateTime createdAt;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Column(name = "status")
    @Convert(converter = FinancialTransactionStatusDataEnumConverter.class)
    private FinancialTransactionStatusDataEnum status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_target_id", referencedColumnName = "account_id")
    private AccountEntity target;

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder(setterPrefix = "with")
    @Getter
    @Setter
    public static class FinancialTransactionEntityId implements Serializable {

        private static final long serialVersionUID = 1L;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "account_id", referencedColumnName = "account_id")
        private AccountEntity source;

        @Column(name = "identifier")
        private UUID identifier;
    }

}
