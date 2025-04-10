package Models;

import Interfaces.Deposit;
import Interfaces.Withdraw;

import java.time.LocalDate;

public class FixedDepositAccount extends BankAccount implements  Withdraw {
    private static final double INTEREST = 0.15;
    private LocalDate maturityDate;

    public FixedDepositAccount(String accountNumber, String ownerName, double balance,String pinCode, LocalDate maturityDate) {
        super(accountNumber, ownerName, balance,pinCode);
        this.maturityDate = maturityDate;
        transactionHistory.add("Created new Fixed Deposit Account "+accountNumber+ " maturing on "+maturityDate+" with "+balance+" created on "+ LocalDate.now());
    }


    @Override
    public void withdraw(double amount) {
        if (LocalDate.now().isAfter(maturityDate) || LocalDate.now().isEqual(maturityDate)) {
            balance +=balance*INTEREST;
            if (amount <= balance) {
                balance -= amount;
                transactionHistory.add("Withdrew after maturity: $" + amount+" on "+LocalDate.now());
                System.out.println("Withdrew after maturity: $" + amount);
            } else {
                System.out.println("Insufficient balance.");
                transactionHistory.add("Withdrawal Failed: Insufficient balance.");
            }
        } else {
            System.out.println("Cannot withdraw before maturity date.");
            transactionHistory.add("Withdrawal Failed: Cannot withdraw before maturity date.");
        }
    }


}
