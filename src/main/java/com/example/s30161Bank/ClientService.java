package com.example.s30161Bank;

import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private ClientStorage clientStorage;

    public ClientService(ClientStorage clientStorage) {
        this.clientStorage = clientStorage;
    }

    public int registerClient(String name, String surname, int saldo) {
        Client client = new Client(name, surname, saldo);
        return clientStorage.saveClient(client);
    }

    public Client getClient(int id) {
        return clientStorage.getClientById(id);
    }
}
