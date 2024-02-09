package org.sid.bankaccountservice.web;

import lombok.AllArgsConstructor;
import org.sid.bankaccountservice.dto.CustomerRequestDTO;
import org.sid.bankaccountservice.dto.CustomerResponseDTO;
import org.sid.bankaccountservice.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class CustomerRestController {
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerResponseDTO> customers (){
        return customerService.customers();
    }

    @GetMapping("/customer/{id}")
    public CustomerResponseDTO customer (@PathVariable Long id){
        return customerService.customer(id);
    }

    @PostMapping("/customer")
    public CustomerResponseDTO addCustomer (@RequestBody CustomerRequestDTO customerRequestDTO){
        return customerService.addCustomer(customerRequestDTO);
    }

    @DeleteMapping("customer/{id}")
    public void delete(@RequestParam Long id){
        customerService.delete(id);
    }

}
