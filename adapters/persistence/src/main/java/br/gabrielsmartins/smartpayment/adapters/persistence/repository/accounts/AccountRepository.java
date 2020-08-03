package br.gabrielsmartins.smartpayment.adapters.persistence.repository.accounts;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.AccountTypeDataEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    @Query(value = "select a from AccountEntity a where a.customerId = :pCustomerId")
    List<AccountEntity> findByCustomerId(@Param("pCustomerId") UUID customerId);

    @Query(value = "select a from AccountEntity a where a.type = :pType")
    List<AccountEntity> findByType(@Param("pType") AccountTypeDataEnum type);

}
