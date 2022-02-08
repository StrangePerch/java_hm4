package com.company;

import com.company.bank.Bank;
import com.company.bank.operation.PutOperation;
import com.company.bank.operation.SendOperation;
import com.company.bank.operation.TakeOperation;
import com.company.bank.tree.AVLTree;

public class Main {

    public static void main(String[] args) {
	    var bank = new Bank();
        var client = bank.createClient("123","123");
        var account = bank.createAccount(client);
        var account2 = bank.createAccount(client);

        bank.executeOperation(new PutOperation(client, account, 5000));
        bank.executeOperation(new PutOperation(client, account2, 313));
        bank.executeOperation(new TakeOperation(client, "123", account2, 113));
        bank.executeOperation(new SendOperation(client, "123", account, account2, 1000));
        bank.executeOperation(new SendOperation(client, "123", account, account2, 10000));
        System.out.println(account);
        System.out.println(account2);
        for (var log:
                bank.logger.getInfo()){
            System.out.println("INFO: " + log);
        }
        for (var log:
                bank.logger.getWarnings()){
            System.out.println("WARNING: " + log);
        }
        for (var log:
                bank.logger.getErrors()){
            System.out.println("ERROR: " + log);
        }


//        AVLTree<Integer, Integer> tree = new AVLTree<>();
//        for (int i = 0; i < 10000; i++) {
//            tree.push(i,i);
//        }
//        tree.menu();
    }
}
