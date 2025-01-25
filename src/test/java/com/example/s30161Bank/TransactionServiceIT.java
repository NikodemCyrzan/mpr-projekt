package com.example.s30161Bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TransactionServiceIT {
    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        clientService.registerClient("Janusz", "Soplica", 12389);
        clientService.registerClient("Adam", "Mickiewicz", 1);
        clientService.registerClient("Jan", "Polski", 23784);
    }

    @Test
    void shouldMakeTransaction() {
        Transaction transaction = transactionService.makeTransaction(0, 12_389);

        assertThat(transaction.status).isEqualTo(TransactionStatus.ACCEPTED);
        assertThat(clientService.getClient(0).getSaldo()).isEqualTo(0);
        assertThat(clientService.getClient(0).getSaldo()).isEqualTo(transaction.newSaldo);
    }

    @Test
    void shouldNotMakeTransactionWhenNotEnoughMoney() {
        Transaction transaction = transactionService.makeTransaction(1, 134);

        assertThat(transaction.status).isEqualTo(TransactionStatus.DECLINED);
        assertThat(clientService.getClient(1).getSaldo()).isEqualTo(1);
        assertThat(clientService.getClient(1).getSaldo()).isEqualTo(transaction.newSaldo);
    }

    @Test
    void shouldNotMakeTransactionWhenNoClient() {
        Transaction transaction = transactionService.makeTransaction(2220, 134);

        assertThat(transaction.status).isEqualTo(TransactionStatus.NO_ACCOUNT);
    }

    @Test
    void shouldAddMoney() {
        Transaction transaction = transactionService.addMoney(2, 1_000_000);

        assertThat(transaction.status).isEqualTo(TransactionStatus.ACCEPTED);
        assertThat(clientService.getClient(2).getSaldo()).isEqualTo(1_023_784);
        assertThat(clientService.getClient(2).getSaldo()).isEqualTo(transaction.newSaldo);
    }

    @Test
    void shouldNotAddMoneyWhenNoClient() {
        Transaction transaction = transactionService.addMoney(2000, 1_000_000);

        assertThat(transaction.status).isEqualTo(TransactionStatus.NO_ACCOUNT);
    }
}
