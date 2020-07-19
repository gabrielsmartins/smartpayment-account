package br.gabrielsmartins.smartpayment.adapters.web.controller;


import br.gabrielsmartins.smartpayment.adapters.web.dto.AccountDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.AccountWebMapper;
import br.gabrielsmartins.smartpayment.application.domain.accounts.Account;
import br.gabrielsmartins.smartpayment.application.port.in.accounts.SaveAccountUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class SaveAccountController {

    private final SaveAccountUseCase useCase;
    private final AccountWebMapper mapper;

    @PostMapping()
    public ResponseEntity<?> save(AccountDTO accountRequestDto){
        Account account = mapper.mapToDomain(accountRequestDto);
        Account savedAccount = useCase.save(account);
        AccountDTO savedAccountDTO = mapper.mapToDto(savedAccount);
        URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").buildAndExpand(savedAccountDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(savedAccountDTO);
    }

}
