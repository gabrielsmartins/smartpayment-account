package br.com.smartpayment.adapters.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponseDto {

    @JsonProperty("id")
    public Long id;
    @JsonProperty("type")
    public String type;

}
