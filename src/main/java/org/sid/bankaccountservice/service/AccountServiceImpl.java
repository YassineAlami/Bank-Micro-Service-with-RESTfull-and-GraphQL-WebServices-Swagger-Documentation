package org.sid.bankaccountservice.service;

import lombok.AllArgsConstructor;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private BankAccountRepository bankAccountRepository;
    private AccountMapper accountMapper;

    @Override
    public List<BankAccount> accounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount account(String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        //no need for all of that.. this will be handled by 'accountMapper.BankAccountFromRequestDTO()' method
        /*BankAccount bankAccount= BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountRequestDTO.getBalance())
                .type(bankAccountRequestDTO.getType())
                .currency(bankAccountRequestDTO.getCurrency())
                .build();*/

        BankAccount bankAccount1= accountMapper.BankAccountFromRequestDTO(bankAccountRequestDTO);
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount1);
        //we won t need all of that code since we ve introduced the mapper class
        //7it the 'BeanUtils.copyProperties...' does the deed of us
        /*BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
                .id(saveBankAccount.getId())
                .createdAt(saveBankAccount.getCreatedAt())
                .balance(saveBankAccount.getBalance())
                .type(saveBankAccount.getType())
                .currency(saveBankAccount.getCurrency())
                .build();*/
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.BankAccountResponseDTOfromAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }

    @Override
    public BankAccount update(String id, BankAccount bankAccount) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if(bankAccount.getBalance()!= null)account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedAt()!=null)account.setCreatedAt(bankAccount.getCreatedAt());
        if(bankAccount.getType()!=null)account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null)account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }

    @Override
    public BankAccountResponseDTO update2(String id, BankAccountRequestDTO bankAccountRequestDTO) {
        //we know that 'accountMapper.BankAccountFromRequestDTO()'
        //so the 'BankAccount' won t have no values for the fields 'id' and 'createdAt'
        BankAccount bankAccount1= accountMapper.BankAccountFromRequestDTO(bankAccountRequestDTO);

        //as a solution to that, here we re going to get the values of the 2 previously mentioned fields using the id
        //and assign value to the fields of 'bankAccount1' object
        bankAccount1.setId(bankAccountRepository.findById(id).orElseThrow().getId());
        bankAccount1.setCreatedAt(bankAccountRepository.findById(id).orElseThrow().getCreatedAt());

        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount1);
        return accountMapper.BankAccountResponseDTOfromAccount(saveBankAccount);
    }

    @Override
    public void delete(String id) {
        bankAccountRepository.deleteById(id);
    }

}
