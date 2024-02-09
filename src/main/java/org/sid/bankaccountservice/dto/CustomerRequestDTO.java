package org.sid.bankaccountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bankaccountservice.entities.BankAccount;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerRequestDTO {
    private String name;
}
