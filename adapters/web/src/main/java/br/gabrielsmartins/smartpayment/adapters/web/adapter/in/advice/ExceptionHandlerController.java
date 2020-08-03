package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.advice;

import br.gabrielsmartins.smartpayment.adapters.web.dto.error.ErrorMessageDTO;
import br.gabrielsmartins.smartpayment.application.exception.InvalidCustomerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerController  {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorMessageDTO handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        ErrorMessageDTO message = new ErrorMessageDTO();
        message.setMessage(ex.getMessage());
        message.setPath(request.getContextPath());

        BindingResult result = ex.getBindingResult();
        result.getFieldErrors().forEach(f -> message.addField(new ErrorMessageDTO.FieldErrorMessageDTO(f.getField(), f.getRejectedValue())));
        return message;
    }


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = {InvalidCustomerException.class})
    @ResponseBody
    public ErrorMessageDTO handleInvalidCustomerException(InvalidCustomerException ex, WebRequest request){
        ErrorMessageDTO message = new ErrorMessageDTO();
        message.setMessage(ex.getMessage());
        message.setPath(request.getContextPath());
        return message;
    }


}
