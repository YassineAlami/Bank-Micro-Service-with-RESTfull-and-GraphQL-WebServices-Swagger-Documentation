package org.sid.bankaccountservice.web;

import lombok.AllArgsConstructor;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.entities.Customer;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.repositories.CustomerRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class AccountGraphQLController {
    private AccountService accountService;
    private CustomerRepository customerRepository;


    //this annotation means that each time we have a query of type 'Query' (the one we declared in schema.graphqls)
    // whenever the  clt demands 'accountsList' then automatically we ll execute a method named 'accountsList()'
    @QueryMapping
    public List<BankAccount> accountsList(){
        return accountService.accounts();
    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument String id){
        return accountService.account(id);
    }

    @MutationMapping
    public BankAccountResponseDTO addBankAccount (@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    //this annotation is used with methods that involve modifications such as "create" , "update" ...
    @MutationMapping
    public BankAccountResponseDTO updateAccount (@Argument String id, @Argument BankAccountRequestDTO bankAccount){
        return accountService.update2(id, bankAccount);
    }

    @MutationMapping
    public Boolean deleteAccount (@Argument String id){
        accountService.delete(id);
        return true;
    }

    @QueryMapping
    public List<Customer> customers(){
        return customerRepository.findAll();
    }

}
