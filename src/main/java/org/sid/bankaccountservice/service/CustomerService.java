package org.sid.bankaccountservice.service;

import org.sid.bankaccountservice.dto.CustomerRequestDTO;
import org.sid.bankaccountservice.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseDTO> customers ();
    CustomerResponseDTO customer (Long id);
    CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO);
    void delete (Long id);

}
