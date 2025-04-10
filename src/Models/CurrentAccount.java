package Models;

import Interfaces.Deposit;
import Interfaces.Withdraw;

import java.time.LocalDate;

public class CurrentAccount extends BankAccount implements Deposit, Withdraw {
    private static final double OVERDRAFT_LIMIT = 100;

    public CurrentAccount(String accountNumber, String ownerName, double balance,String pinCode) {
        super(accountNumber, ownerName, balance,pinCode);
        transactionHistory.add("Created new Current Account "+accountNumber+" with "+balance+" on "+ LocalDate.now());
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: $" + amount+" on "+ LocalDate.now());
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount > OVERDRAFT_LIMIT) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount+" on "+ LocalDate.now());
        }else {
            transactionHistory.add("Withdrawal Failed: Insufficient Balance");
        }
    }
}
