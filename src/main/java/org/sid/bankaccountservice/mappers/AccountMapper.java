package org.sid.bankaccountservice.mappers;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class AccountMapper {
    //makes a 'BankAccountResponseDTO' out of a 'BankAccount'
    public BankAccountResponseDTO BankAccountResponseDTOfromAccount(BankAccount bankAccount){

        BankAccountResponseDTO bankAccountResponseDTO=new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }

    //makes a 'BankAccountResponseDTO' out of a 'BankAccount'
    public BankAccount BankAccountFromRequestDTO(BankAccountRequestDTO bankAccountRequestDTO){
        BankAccount bankAccount=new BankAccount();
        //here in case of creating a new account.. we had to add those 2 attributes ourselves cuz the source object ('bankAccountRequestDTO') does not have them yet
        //but in cas these 2 attributes already have value, this means we re up for an update
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        BeanUtils.copyProperties(bankAccountRequestDTO,bankAccount);
        return bankAccount;
    }
}