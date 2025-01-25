package com.example.s30161Bank;

public class Transaction {
    public TransactionStatus status;
    public double newSaldo;

    public Transaction(TransactionStatus status, double newSaldo) {
        this.status = status;
        this.newSaldo = newSaldo;
    }
}
