package com.example.s30161Bank;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private ClientStorage clientStorage;

    public TransactionService(ClientStorage clientStorage) {
        this.clientStorage = clientStorage;
    }

    public Transaction makeTransaction(int clientId, double quota) {
        Client client = clientStorage.getClientById(clientId);

        if (client == null) {
            return new Transaction(TransactionStatus.NO_ACCOUNT, 0);
        }

        if (client.getSaldo() >= quota) {
            client.setSaldo(client.getSaldo() - quota);
            return new Transaction(TransactionStatus.ACCEPTED, client.getSaldo());
        } else {
            return new Transaction(TransactionStatus.DECLINED, client.getSaldo());
        }
    }

    public Transaction addMoney(int clientId, double quota) {
        Client client = clientStorage.getClientById(clientId);

        if (client == null) {
            return new Transaction(TransactionStatus.NO_ACCOUNT, 0);
        }

        client.setSaldo(client.getSaldo() + quota);

        return new Transaction(TransactionStatus.ACCEPTED, client.getSaldo());
    }
}
