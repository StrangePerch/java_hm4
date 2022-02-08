package com.company.bank.operation;

import com.company.bank.exceptions.OperationException;

public interface IOperation {
    void execute() throws OperationException;
    boolean canExecute() throws OperationException;
    String toString();
}
