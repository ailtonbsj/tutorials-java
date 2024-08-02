import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import io.bank.demo.Account;
import io.bank.demo.AccountManager;

public class AccountManagerTest {

    AccountManager bank;
    Account account1;
    Account account2;

    @Before
    public void setUp() {
        account1 = new Account(1L, 200.0, true);
        account2 = new Account(2L, 50.0, true);
        List<Account> bankAccounts = Arrays.asList(account1, account2);
        bank = new AccountManager(bankAccounts);
    }

    @After
    public void tearDown(){
        bank = null;
        account1 = null;
        account2 = null;
    }

    @Test
    public void testTransferValue() {
        boolean result = bank.transferValue(1L, 100.0, 2L);

        assertThat(account2.getBalance(), is(150.0));
        assertThat(account1.getBalance(), is(100.0));
        assertTrue(result);
    }

    @Test
    public void testTryTransferMoreValueThanOwns() {
        boolean result = bank.transferValue(1L, 250.0, 2L);

        assertThat(account2.getBalance(), is(50.0));
        assertThat(account1.getBalance(), is(200.0));
        assertFalse(result);
    }

    @Test
    public void testTryTransferAllValue() {        
        boolean result = bank.transferValue(1L, 200.0, 2L);

        assertThat(account2.getBalance(), is(250.0));
        assertThat(account1.getBalance(), is(0.0));
        assertTrue(result);
    }

    @Test
    public void testFindByAccount() {
        Account account = bank.findByAccountId(2L);

        assertThat(account.getId(), is(2L));
    }

    @Test
    public void testFindByAnUnexistentAccount() {
        Account account = bank.findByAccountId(101L);

        assertNull(account);
    }

}
