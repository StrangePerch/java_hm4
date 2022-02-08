package com.company.bank.operation;

import com.company.bank.Bank;
import com.company.bank.Client;
import com.company.bank.exceptions.OperationException;

public class PutOperation implements IOperation{

    private Bank.Account account;
    private Client client;
    private double amount;

    public PutOperation(Client client, Bank.Account account, double amount)
    {
        this.client = client;
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() throws OperationException {
        if(canExecute())
        {
            account.putMoney(amount);
        }
    }

    @Override
    public boolean canExecute(){
        return true;
    }

    @Override
    public String toString() {
        return "Client: " + client.getName() + " put " + amount + " on " + account.getId();
    }
}
