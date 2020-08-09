package br.gabrielsmartins.smartpayment.adapters.web.adapter.out.customers;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customers", url = "${customers.endpoint}")
public interface CustomerWebService {

    @GetMapping("/{id}")
    ResponseEntity<CustomerDTO> findById(@PathVariable("id") String id);

}
