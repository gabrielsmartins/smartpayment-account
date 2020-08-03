package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.accounts;


import br.gabrielsmartins.smartpayment.adapters.web.mapper.accounts.AccountWebMapper;
import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.domain.enums.AccountTypeEnum;
import br.gabrielsmartins.smartpayment.application.exception.InvalidCustomerException;
import br.gabrielsmartins.smartpayment.application.port.in.accounts.SearchAccountUseCase;
import br.gabrielsmartins.smartpayment.common.stereotype.WebAdapter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebAdapter
@RestController
@RequestMapping("/v1/accounts")
@AllArgsConstructor
public class SearchAccountController {

    private SearchAccountUseCase useCase;
    private AccountWebMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") UUID id) throws InvalidCustomerException {
        Optional<Account> optionalAccount = useCase.findById(id);
        if(!optionalAccount.isPresent())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(mapper.mapToDTO(optionalAccount.get()), HttpStatus.OK);
    }

    @GetMapping(params = {"customer_id"})
    public ResponseEntity<?> findByCustomerId(@RequestParam("customer_id")UUID customerId) throws InvalidCustomerException {
        List<Account> accounts = useCase.findByCustomerId(customerId);
        if(accounts.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(mapper.mapToDTO(accounts), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> findByType(@RequestParam("account_type")String accountType) throws InvalidCustomerException {
        List<Account> accounts = useCase.findByType(AccountTypeEnum.valueOf(accountType));
        if(accounts.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(mapper.mapToDTO(accounts), HttpStatus.OK);
    }


}
