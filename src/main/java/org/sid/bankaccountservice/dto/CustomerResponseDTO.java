package org.sid.bankaccountservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bankaccountservice.entities.BankAccount;

import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private List<BankAccountResponseDTO> bankAccounts;
}
