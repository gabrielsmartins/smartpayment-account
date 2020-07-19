package br.gabrielsmartins.smartpayment.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {
}
