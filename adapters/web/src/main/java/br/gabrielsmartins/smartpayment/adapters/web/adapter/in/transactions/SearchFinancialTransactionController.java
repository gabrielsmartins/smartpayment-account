package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.transactions;

import br.gabrielsmartins.smartpayment.adapters.web.mapper.transactions.FinancialTransactionWebMapper;
import br.gabrielsmartins.smartpayment.application.domain.transactions.FinancialTransaction;
import br.gabrielsmartins.smartpayment.application.port.in.transactions.SearchFinancialTransactionUseCase;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/financial_transactions")
@AllArgsConstructor
public class SearchFinancialTransactionController {

    private SearchFinancialTransactionUseCase useCase;
    private FinancialTransactionWebMapper mapper;

    @GetMapping(params = {"account_id","start_date_time","end_date_time"})
    public ResponseEntity<?> findByAccountIdAndInterval(@RequestHeader HttpHeaders httpHeaders,
                                                        @RequestParam(value = "account_id", required = true)UUID accountId,
                                                        @RequestParam(value = "start_date_time", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDatetime,
                                                        @RequestParam(value = "end_date_time", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime endDatetime){

        List<FinancialTransaction> transactions = useCase.findByAccountIdAndInterval(accountId, startDatetime, endDatetime);
        if(transactions.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(mapper.mapToDTO(transactions), HttpStatus.OK);
    }

    @GetMapping(params = {"customer_id","start_date_time","end_date_time"})
    public ResponseEntity<?> findByCustomerIdAndInterval(@RequestHeader HttpHeaders httpHeaders,
                                                        @RequestParam(value = "customer_id", required = true)String customerId,
                                                        @RequestParam(value = "start_date_time", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDatetime,
                                                        @RequestParam(value = "end_date_time", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime endDatetime){

        List<FinancialTransaction> transactions = useCase.findByCustomerIdAndInterval(customerId, startDatetime, endDatetime);
        if(transactions.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(mapper.mapToDTO(transactions), HttpStatus.OK);
    }

}
