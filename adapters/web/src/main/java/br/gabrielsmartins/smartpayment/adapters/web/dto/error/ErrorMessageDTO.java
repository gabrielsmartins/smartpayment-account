package br.gabrielsmartins.smartpayment.adapters.web.dto.error;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
public class ErrorMessageDTO {

    @JsonProperty("message")
    private String message;

    @JsonProperty("path")
    private String path;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("fields")
    private List<FieldErrorMessageDTO> fields = new LinkedList<>();

    public Integer addField(FieldErrorMessageDTO field) {
        this.fields.add(field);
        return this.fields.size();
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldErrorMessageDTO {

        @JsonProperty("field")
        private String description;

        @JsonProperty("value")
        private Object value;

    }

}
