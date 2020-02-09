package br.com.smartpayment.adapters.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequestDto {

    @JsonProperty("type")
    private String type;
}
