package Models;

import Interfaces.Deposit;
import Interfaces.Withdraw;

import java.time.LocalDate;

public class SavingsAccount extends BankAccount implements Deposit, Withdraw {
    public static final double MIN_BALANCE = 100;


    public SavingsAccount(String accountNumber, String ownerName, double balance, String pinCode) {

        super(accountNumber, ownerName, balance,pinCode);
        transactionHistory.add("Created new Savings Account "+accountNumber+" with "+balance+" on "+ LocalDate.now());

    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: $" + amount+" on "+ LocalDate.now());
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount > MIN_BALANCE) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount+" on "+LocalDate.now());
        }else {
            transactionHistory.add("Withdrawal Failed: Insufficient Balance");
        }
    }
}
