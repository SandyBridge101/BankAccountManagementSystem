package Models;


import DataStructures.TransactionLinkedList;
import DataStructures.TransactionNode;

import java.time.LocalDate;

public abstract class BankAccount {
    protected double balance;
    protected String accountNumber;
    protected String ownerName;
    protected String pinCode;//for security
    protected TransactionLinkedList transactionHistory;


    public BankAccount(String accountNumber, String ownerName, double balance, String pinCode) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.pinCode = pinCode;
        this.transactionHistory = new TransactionLinkedList();

    }

    public void viewTransactionHistory() {
        transactionHistory.display();
    }

    public double getBalance() {
        return balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public TransactionLinkedList getTransactionHistory() {return transactionHistory;}

    public boolean verifyPinCode(String inputCode) {
        return inputCode.equals(this.pinCode);
    }
    
}