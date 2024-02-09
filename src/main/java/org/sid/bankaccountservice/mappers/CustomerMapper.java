package org.sid.bankaccountservice.mappers;

import lombok.AllArgsConstructor;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.dto.CustomerRequestDTO;
import org.sid.bankaccountservice.dto.CustomerResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component
@AllArgsConstructor
public class CustomerMapper {
    private AccountMapper accountMapper;

    public CustomerResponseDTO fromCustomerToResponseDTO (Customer customer){
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        List <BankAccountResponseDTO> bankAccountResponseDTOs = new ArrayList<>();

        customerResponseDTO.setId(customer.getId());
        customerResponseDTO.setName(customer.getName());
        for (BankAccount bankAccount : customer.getBankAccounts()) {
            bankAccountResponseDTOs.add(accountMapper.BankAccountResponseDTOfromAccount(bankAccount));
        }
        customerResponseDTO.setBankAccounts(bankAccountResponseDTOs);
        return customerResponseDTO;
    }

    public Customer fromRequestDTOToCustomer (CustomerRequestDTO customerRequestDTO){
        Customer customer = new Customer();

        List<BankAccount> bankAccounts = new ArrayList<>();
        customer.setBankAccounts(bankAccounts);

        customer.setName(customerRequestDTO.getName());
        return customer;
    }
}
