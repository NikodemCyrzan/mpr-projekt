package com.example.s30161Bank;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ClientStorage {
    private HashMap<Integer, Client> clients = new HashMap<Integer, Client>();

    public int saveClient(Client client) {
        int id = clients.size();

        clients.put(id, client);

        return id;
    }

    public Client getClientById(int id) {
        return clients.get(id);
    }
}
