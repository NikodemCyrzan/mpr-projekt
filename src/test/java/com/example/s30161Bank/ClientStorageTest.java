package com.example.s30161Bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ClientStorageTest {
    private ClientStorage clientStorage;

    @BeforeEach
    void setup() {
        clientStorage = new ClientStorage();
    }

    @Test
    void shouldSaveClient() {
        Client client = new Client("Jan", "Kowalski", 10000);

        int id = clientStorage.saveClient(client);

        assertThat(clientStorage.getClientById(id).getName()).isEqualTo(client.getName());
    }
}
