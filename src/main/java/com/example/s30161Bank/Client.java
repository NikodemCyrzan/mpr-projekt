package com.example.s30161Bank;

public class Client {
    private double saldo;
    private String name;
    private String surname;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Client(String name, String surname, double saldo) {
        this.saldo = saldo;
        this.name = name;
        this.surname = surname;
    }
}
