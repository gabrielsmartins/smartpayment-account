package br.com.smartpayment.adapters.web.controller;


import br.com.smartpayment.adapters.web.dto.AccountRequestDto;
import br.com.smartpayment.adapters.web.dto.AccountResponseDto;
import br.com.smartpayment.adapters.web.mapper.AccountWebMapper;
import br.com.smartpayment.adapters.web.service.AccountService;
import br.com.smartpayment.application.domain.Account;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService service;
    private final AccountWebMapper mapper;

    @PostMapping()
    public ResponseEntity<?> create(AccountRequestDto accountRequestDto){
        Account account = mapper.mapToDomain(accountRequestDto);
        Account createdAccount = service.create(account);
        AccountResponseDto accountResponseDto = mapper.mapToDto(createdAccount);
        return new ResponseEntity<>(accountResponseDto, HttpStatus.CREATED);
    }

}
