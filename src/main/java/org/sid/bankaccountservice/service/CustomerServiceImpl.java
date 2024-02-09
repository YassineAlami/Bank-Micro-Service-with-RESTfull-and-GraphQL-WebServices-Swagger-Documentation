package org.sid.bankaccountservice.service;

import ch.qos.logback.core.util.COWArrayList;
import lombok.AllArgsConstructor;
import org.sid.bankaccountservice.dto.CustomerRequestDTO;
import org.sid.bankaccountservice.dto.CustomerResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.entities.Customer;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.mappers.CustomerMapper;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private AccountMapper accountMapper;
    private BankAccountRepository bankAccountRepository;

    @Override
    public List<CustomerResponseDTO> customers() {
        List <CustomerResponseDTO> customerResponseDTOs = new ArrayList<>();
        for (Customer c: customerRepository.findAll()) {
            customerResponseDTOs.add(customerMapper.fromCustomerToResponseDTO(c));
        }
        return customerResponseDTOs;
    }

    @Override
    public CustomerResponseDTO customer(Long id) {
        return customerMapper.fromCustomerToResponseDTO(customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("No Account Has This Id"))));
    }

    @Override
    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.fromRequestDTOToCustomer(customerRequestDTO);
        customerRepository.save(customer);
        return customerMapper.fromCustomerToResponseDTO(customer);
    }


    @Override
    public void delete(Long id) {
        for (BankAccount b: customerRepository.findById(id).get().getBankAccounts()) {
            bankAccountRepository.deleteById(b.getId());
        }
        customerRepository.deleteById(id);
    }
}