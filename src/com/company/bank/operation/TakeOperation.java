package com.company.bank.operation;

import com.company.bank.Bank;
import com.company.bank.Client;
import com.company.bank.exceptions.OperationException;

public class TakeOperation implements IOperation{

    private Bank.Account account;
    private Client client;
    private String password;
    private double amount;

    public TakeOperation(Client client, String password, Bank.Account account, double amount)
    {
        this.client = client;
        this.password = password;
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() throws OperationException {
        if(canExecute())
        {
            account.takeMoney(password, amount);
        }
    }

    @Override
    public boolean canExecute() throws OperationException {
        if(account.availableBalance() < amount)
        {
            throw new OperationException("Not enough balance");
        }
        if(!client.tryLogin(password))
        {
            throw new OperationException("Wrong password");
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client: " + client.getName() + " took " + amount + " from " + account.getId();
    }
}
