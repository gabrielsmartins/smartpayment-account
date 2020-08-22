package br.gabrielsmartins.smartpayment.adapters.persistence.repository.transactions;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions.FinancialTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FinancialTransactionRepository extends JpaRepository<FinancialTransactionEntity, FinancialTransactionEntity.FinancialTransactionEntityId> {

    @Query(value = "select f from FinancialTransactionEntity f " +
            "join f.id.source a " +
            "where f.id.source.id = :pAccountId " +
            "and f.createdAt between :pStartDatetime and :pEndDatetime")
    List<FinancialTransactionEntity> findByAccountIdAndInterval(@Param("pAccountId") UUID accountId,
                                                                @Param("pStartDatetime") LocalDateTime startDatetime,
                                                                @Param("pEndDatetime") LocalDateTime endDatetime);


    @Query(value = "select f from FinancialTransactionEntity f " +
            "join f.id.source a " +
            "where a.customerId = :pCustomerId " +
            "and f.createdAt between :pStartDatetime and :pEndDatetime")
    List<FinancialTransactionEntity> findByCustomerIdAndInterval(@Param("pCustomerId") String customerId,
                                                                 @Param("pStartDatetime") LocalDateTime startDatetime,
                                                                 @Param("pEndDatetime") LocalDateTime endDatetime);
}
