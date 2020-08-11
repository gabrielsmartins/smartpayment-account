package br.gabrielsmartins.smartpayment.adapters.web.dto.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(value = "customer_id")
    private String customerId;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "balance", access = JsonProperty.Access.WRITE_ONLY)
    private BigDecimal balance;

}
