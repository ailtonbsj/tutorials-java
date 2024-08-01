package io.bank.demo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountManager {
    private List<Account> bankAccounts;
}
