package br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.FinancialTransactionStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.converter.FinancialTransactionStatusDataEnumConverter;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(setterPrefix = "with")
@Getter
@Setter
@Table(name = "transaction")
@Entity
public class FinancialTransactionEntity {

    private FinancialTransactionEntityId id;

    @Column(name = "start")
    private LocalDateTime start;

    @Column(name = "end")
    private LocalDateTime end;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Column(name = "status")
    @Convert(converter = FinancialTransactionStatusDataEnumConverter.class)
    private FinancialTransactionStatusDataEnum status;

    @OneToOne
    @JoinColumn(name = "target", referencedColumnName = "target")
    private AccountEntity target;

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder(setterPrefix = "with")
    @Getter
    @Setter
    public static class FinancialTransactionEntityId{

        @ManyToOne
        @JoinColumn(name = "source", referencedColumnName = "source")
        private AccountEntity source;

        @Column(name = "identifier")
        private UUID identifier;
    }

}
