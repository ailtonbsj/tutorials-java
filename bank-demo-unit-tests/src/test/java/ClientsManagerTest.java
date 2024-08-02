import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import io.bank.demo.AgeNotAllowedException;
import io.bank.demo.Client;
import io.bank.demo.ClientsManager;

public class ClientsManagerTest {

    @Test
    public void testFindByClient() {
        // Mount
        Client client1 = new Client(1L, "Fulano", 31, "fulano@gmail.com", true, 1L);
        Client client2 = new Client(2L, "Ciclano", 18, "ciclano@gmail.com", true, 2L);
        List<Client> clients = List.of(client1, client2);
        ClientsManager bankClients = new ClientsManager(clients);

        // Run
        Client client = bankClients.findByClientId(2L);

        // Check
        assertThat(client.getId(), is(2L));
        assertThat(client.getEmail(), is("ciclano@gmail.com"));
    }

    @Test
    public void testRemoveClient() {
        // Mount
        Client client1 = new Client(1L, "Fulano", 31, "fulano@gmail.com", true, 1L);
        Client client2 = new Client(2L, "Ciclano", 18, "ciclano@gmail.com", true, 2L);
        List<Client> clients = new ArrayList<>(Arrays.asList(client1, client2));
        ClientsManager bankClients = new ClientsManager(clients);

        // Run
        boolean isRemoved = bankClients.removeClientById(1L);

        // Check
        assertThat(isRemoved, is(true));
        assertThat(bankClients.getBankClients().size(), is(1));
        assertNull(bankClients.findByClientId(1L));
    }

    @Test
    public void testValidateAgeWithNotAllowedValue(){
        //Mount
        Client client1 = new Client(1L, "Novinha", 17, "nova@gmail.com", true, 1L);
        List<Client> clients = new ArrayList<>(Arrays.asList(client1));
        ClientsManager bankClients = new ClientsManager(clients);

        try {
            // Run
            bankClients.validateAge(client1.getAge());
            fail();
        } catch (Exception e) {
            // Check
            assertThat(e.getMessage(), is(AgeNotAllowedException.MESSAGE));
        }
    }

}
