package io.bank.demo;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountManager {
    private List<Account> bankAccounts;

    public Account findByAccountId(Long id) {
        Optional<Account> account = bankAccounts.stream()
            .filter(c -> c.getId() == id).findFirst();
        if(account.isPresent()) return account.get();
        else return null;
    }

    public boolean transferValue(Long idSource, Double value, Long idTarget) {
        Account source = findByAccountId(idSource);
        Account target = findByAccountId(idTarget);
        if(source != null && target != null && value > 0) {
            if(source.getBalance() >= value) {
                source.setBalance(source.getBalance() - value);
                target.setBalance(target.getBalance() + value);
                return true;
            }
        }
        return false;
    }
}
