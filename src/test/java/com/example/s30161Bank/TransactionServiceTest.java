package com.example.s30161Bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private ClientStorage clientStorage;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void shouldMakeTransaction() {
        Client client = new Client("Jan", "Kowalski", 1000);
        when(clientStorage.getClientById(anyInt())).thenReturn(client);

        Transaction transaction = transactionService.makeTransaction(0, 200);

        assertThat(transaction.status).isEqualTo(TransactionStatus.ACCEPTED);
        assertThat(client.getSaldo()).isEqualTo(800);
    }

    @Test
    void shouldNotMakeTransactionWhenNoClient() {
        when(clientStorage.getClientById(anyInt())).thenReturn(null);

        Transaction transaction = transactionService.makeTransaction(0, 200);

        assertThat(transaction.status).isEqualTo(TransactionStatus.NO_ACCOUNT);
    }

    @Test
    void shouldNotMakeTransactionWhenNotEnoughMoney() {
        Client client = new Client("Jan", "Kowalski", 100);
        when(clientStorage.getClientById(anyInt())).thenReturn(client);

        Transaction transaction = transactionService.makeTransaction(0, 200);

        assertThat(transaction.status).isEqualTo(TransactionStatus.DECLINED);
        assertThat(client.getSaldo()).isEqualTo(100);
    }

    @Test
    void shouldAddMoney() {
        Client client = new Client("Jan", "Kowalski", 200);
        when(clientStorage.getClientById(anyInt())).thenReturn(client);

        Transaction transaction = transactionService.addMoney(0, 200);

        assertThat(transaction.newSaldo).isEqualTo(400);
        assertThat(client.getSaldo()).isEqualTo(400);
    }

    @Test
    void shouldNotAddMoneyWhenNoUser() {
        when(clientStorage.getClientById(anyInt())).thenReturn(null);

        Transaction transaction = transactionService.addMoney(0, 200);

        assertThat(transaction.status).isEqualTo(TransactionStatus.NO_ACCOUNT);
    }
}
