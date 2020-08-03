package br.gabrielsmartins.smartpayment.adapters.web.adapter.out.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class CustomerDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "document_type")
    private String documentType;

    @JsonProperty(value = "document_number")
    private String documentNumber;

    @JsonProperty(value = "category_id")
    private String categoryId;
}
