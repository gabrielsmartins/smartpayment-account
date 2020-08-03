package br.gabrielsmartins.smartpayment.adapters.persistence.mapper.transactions;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions.FinancialTransactionEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.FinancialTransactionStatusDataEnum;
import br.gabrielsmartins.smartpayment.application.domain.enums.FinancialTransactionStatusEnum;
import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface FinancialTransactionPersistenceMapper {

    @Mappings({
           @Mapping(source = "status", target = "status", qualifiedByName = "toType"),
           @Mapping(target = "id.source", ignore = true),
           @Mapping(target = "target", ignore = true)
    })
    FinancialTransactionEntity mapToEntity(FinancialTransaction financialTransactionEntity);

    List<FinancialTransactionEntity> mapToEntity(List<FinancialTransaction> financialTransactionEntity);

    @Mappings({
            @Mapping(source = "status", target = "status", qualifiedByName = "fromType"),
            @Mapping(target = "id.source", ignore = true),
            @Mapping(target = "target", ignore = true)
    })
    FinancialTransaction mapToDomain(FinancialTransactionEntity financialTransactionEntity);

    List<FinancialTransaction> mapToDomain(List<FinancialTransactionEntity> financialTransactionEntity);

    default FinancialTransactionStatusDataEnum toType(FinancialTransactionStatusEnum statusEnum){
        return FinancialTransactionStatusDataEnum.fromEnum(statusEnum);
    }

    default FinancialTransactionStatusEnum fromType(FinancialTransactionStatusDataEnum statusDataEnum){
        return statusDataEnum.getEnumValue();
    }

}
