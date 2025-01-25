package com.example.s30161Bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientStorage clientStorage;

    @InjectMocks
    private ClientService clientService;

    @Test
    void shouldRegisterClient() {
        when(clientStorage.saveClient(any())).thenReturn(10);

        int id = clientService.registerClient("Jan", "Kowalski", 20);

        assertThat(id).isEqualTo(10);
    }

    @Test
    void shouldGetClient() {
        when(clientStorage.getClientById(anyInt())).thenReturn(new Client("Jan", "Kowalski", 200));

        Client client = clientService.getClient(0);

        assertThat(client.getName()).isEqualTo("Jan");
    }
}
