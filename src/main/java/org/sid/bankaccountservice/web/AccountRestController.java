package org.sid.bankaccountservice.web;

import lombok.AllArgsConstructor;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class AccountRestController {
    private AccountService accountService;


    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return accountService.accounts();
    }

    @GetMapping("/bankAccount/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return accountService.account(id);
    }

    @PostMapping("bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
        return accountService.addAccount(requestDTO);
    }

    /*@PutMapping("bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount){
        return accountService.update(id,bankAccount);
    }*/

    @PutMapping("bankAccounts/{id}")
    public BankAccountResponseDTO updateDTO(@PathVariable String id, @RequestBody BankAccountRequestDTO bankAccountRequestDTO){
        return accountService.update2(id,bankAccountRequestDTO);
    }

    @DeleteMapping("bankAccounts/{id}")
    public void delete(@RequestParam String id){
        accountService.delete(id);
    }
}
