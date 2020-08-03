package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.accounts;


import br.gabrielsmartins.smartpayment.adapters.web.dto.accounts.AccountDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.accounts.AccountWebMapper;
import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.exception.InvalidCustomerException;
import br.gabrielsmartins.smartpayment.application.port.in.accounts.CreateAccountUseCase;
import br.gabrielsmartins.smartpayment.common.stereotype.WebAdapter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@WebAdapter
@RestController
@RequestMapping("/v1/accounts")
@AllArgsConstructor
public class CreateAccountController {

    private CreateAccountUseCase useCase;
    private AccountWebMapper mapper;

    @PostMapping()
    public ResponseEntity<?> save(AccountDTO accountRequestDto) throws InvalidCustomerException {
        Account account = mapper.mapToDomain(accountRequestDto);
        Account savedAccount = useCase.create(account);
        AccountDTO savedAccountDTO = mapper.mapToDTO(savedAccount);
        URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").buildAndExpand(savedAccountDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(savedAccountDTO);
    }

}
