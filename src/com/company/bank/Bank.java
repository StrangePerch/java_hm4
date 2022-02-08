package com.company.bank;

import com.company.bank.exceptions.OperationException;
import com.company.bank.logger.Logger;
import com.company.bank.operation.IOperation;
import com.company.bank.tree.AVLTree;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.LinkedList;

public class Bank {
    private final AVLTree<Client, String> clients = new AVLTree<>();
    private final AVLTree<Account, Integer> accounts = new AVLTree<>();
    private final LinkedList<IOperation> operations = new LinkedList<>();
    public final Logger logger = new Logger();

    private static int accountNumber = 0;

    private static int getAccountNumber() {
        return accountNumber++;
    }

    public Client createClient(String name, String password) {
        if (clients.get(name) == null) {
            return new Client(name, password);
        } else {
            logger.LogError("User already exist: " + name);
            throw new KeyAlreadyExistsException();
        }
    }

    public Client getClient(String name, String password) {
        var client = clients.get(name);
        if(client != null && client.tryLogin(password))
            return client;
        return null;
    }

    public Account createAccount(Client client) {
        var account = new Account(client);
        client.addAccount(account);
        accounts.push(account, account.getId());
        logger.LogInfo("Account created id: " + account.getId() + " client: " + account.getClient());
        return account;
    }

    public void printLogs()
    {
        operations.stream().map(IOperation::toString).forEach(System.out::println);
    }

    public boolean executeOperation(IOperation operation) {
        try {
            if (!operation.canExecute())
                return false;
            operation.execute();
            operations.add(operation);
            logger.LogInfo(operation.toString());
            return false;
        } catch (OperationException e) {
            logger.LogError(e.toString());
            return false;
        }
    }

    public class Account {
        private double balance;
        private final int number;
        private Client client;

        private Account(Client client) {
            this.balance = 0;
            this.number = Bank.getAccountNumber();
            this.client = client;
        }

        public Client getClient() {
            return client;
        }

        public int getId() {
            return number;
        }

        public double availableBalance() {
            return balance;
        }

        public double takeMoney(String password, double amount) {
            if (client.tryLogin(password)) {
                balance -= amount;
                return amount;
            }
            return 0;
        }

        public void putMoney(double amount) {
            balance += amount;
        }

        public String toString(){
            return "Account #" + number + " owner: " + client.toString() + " balance: " + balance;
        }
    }
}
