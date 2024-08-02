import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.bank.demo.Account;
import io.bank.demo.AccountManager;

public class AccountManagerTest {

    @Test
    public void testTransferValue() {
        // Mount
        Account account1 = new Account(1L, 200.0, true);
        Account account2 = new Account(2L, 50.0, true);
        List<Account> bankAccounts = Arrays.asList(account1, account2);
        AccountManager bank = new AccountManager(bankAccounts);
        
        // Run
        boolean result = bank.transferValue(1L, 100.0, 2L);

        //Check
        assertThat(account2.getBalance(), is(150.0));
        assertThat(account1.getBalance(), is(100.0));
        assertTrue(result);
    }

    @Test
    public void testTryTransferMoreValueThanOwns() {
        // Mount
        Account account1 = new Account(1L, 50.0, true);
        Account account2 = new Account(2L, 100.0, true);
        List<Account> bankAccounts = Arrays.asList(account1, account2);
        AccountManager bank = new AccountManager(bankAccounts);
        
        // Run
        boolean result = bank.transferValue(1L, 200.0, 2L);

        //Check
        assertThat(account2.getBalance(), is(100.0));
        assertThat(account1.getBalance(), is(50.0));
        assertFalse(result);
    }

    @Test
    public void testTryTransferAllValue() {
        // Mount
        Account account1 = new Account(1L, 50.0, true);
        Account account2 = new Account(2L, 100.0, true);
        List<Account> bankAccounts = Arrays.asList(account1, account2);
        AccountManager bank = new AccountManager(bankAccounts);
        
        // Run
        boolean result = bank.transferValue(1L, 50.0, 2L);

        //Check
        assertThat(account2.getBalance(), is(150.0));
        assertThat(account1.getBalance(), is(0.0));
        assertTrue(result);
    }

}
