package br.gabrielsmartins.smartpayment.adapters.web.mapper.transactions;

import br.gabrielsmartins.smartpayment.adapters.web.dto.transactions.FinancialTransactionDTO;
import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FinancialTransactionWebMapper {

    @Mappings({
            @Mapping(source = "id.source.id", target = "accountId"),
            @Mapping(source = "id.identifier", target = "identifier"),
            @Mapping(source = "target.id", target = "accountTargetId")
    })
    FinancialTransactionDTO mapToDTO(FinancialTransaction financialTransaction);

    List<FinancialTransactionDTO> mapToDTO(List<FinancialTransaction> financialTransactions);

}
