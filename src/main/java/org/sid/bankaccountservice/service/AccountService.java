package org.sid.bankaccountservice.service;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
    List<BankAccount> accounts();
    BankAccount account(String id);
    BankAccount update(String id, BankAccount bankAccount);
    BankAccountResponseDTO update2(String id, BankAccountRequestDTO bankAccountDTO);
    void delete(String id);

}
