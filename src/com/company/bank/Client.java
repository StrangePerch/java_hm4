package com.company.bank;

import java.util.LinkedList;
import java.util.Objects;

public class Client {
    private static int clientCount = 0;

    private static int getClientId()
    {
        return clientCount++;
    }

    private final int id;
    private String name;
    private String password;
    private LinkedList<Bank.Account> accounts = new LinkedList<Bank.Account>();

    public void addAccount(Bank.Account account)
    {
        accounts.add(account);
    }

    public String getName() {
        return name;
    }

    public Client(String name, String password)
    {
        this.name = name;
        this.id = getClientId();
        this.password = password;
    }

    public boolean tryLogin(String password)
    {
        return tryLogin(password, name);
    }

    public boolean tryLogin(String password, String username)
    {
        return Objects.equals(this.name, username) && Objects.equals(this.password, password);
    }

    public int getId() {
        return id;
    }

    public String toString()
    {
        return name;
    }
}
