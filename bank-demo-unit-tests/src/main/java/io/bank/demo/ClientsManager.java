package io.bank.demo;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientsManager {
    private List<Client> bankClients;

    public Client findByClientId(Long id) {
        Optional<Client> client = bankClients
            .stream().filter(c -> c.getId() == id).findFirst();
        if(client.isPresent()) return client.get();
        else return null;
    }

    public boolean removeClientById(Long id) {
        return bankClients.removeIf(client -> client.getId() == id);
    }
}
