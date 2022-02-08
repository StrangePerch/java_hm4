package com.company.bank.operation;

import com.company.bank.Bank;
import com.company.bank.Client;
import com.company.bank.exceptions.OperationException;

public class SendOperation implements IOperation {

    private Bank.Account from;
    private Bank.Account to;
    private Client client;
    private String password;
    private double amount;

    public SendOperation(Client client, String password, Bank.Account from, Bank.Account to, double amount)
    {
        this.client = client;
        this.password = password;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void execute() throws OperationException {
        if(canExecute())
        {
            var money = from.takeMoney(password, amount);
            to.putMoney(money);
        }
    }

    @Override
    public boolean canExecute() throws OperationException {
        if(from.availableBalance() < amount)
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
        return "Client: " + client.getName() + " transferred " + amount + " from " + from.getId() + " to " + to.getId();
    }
}
