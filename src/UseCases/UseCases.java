package UseCases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DataStructures.AccountList;
import DataStructures.TransactionLinkedList;
import Models.BankAccount;
import Models.CurrentAccount;
import Models.FixedDepositAccount;
import Models.SavingsAccount;
import Utils.Utils;

public class UseCases extends AccountList {

    public UseCases() {
        super();
    }
    public String createAccount(String ownerName,double amount,String pinCode,String accountType){
        if(accountType.equals("Savings")){
            if(amount >= SavingsAccount.MIN_BALANCE){
                SavingsAccount newSavingsAccount = new SavingsAccount(Utils.generateAccountNumber(), ownerName, amount, pinCode);
                AccountList.savingsAccounts.add(newSavingsAccount);
                return newSavingsAccount.getTransactionHistory().getRecent();
            }else{
                return "Initial Balance is below minimum deposit amount";
            }
        }
        else{
            CurrentAccount newCurrentAccount = new CurrentAccount(Utils.generateAccountNumber(), ownerName, amount, pinCode);
            AccountList.currentAccounts.add(newCurrentAccount);
            return newCurrentAccount.getTransactionHistory().getRecent();
        }


    }

    //overloaded function for fixed deposit
    public String createAccount(String ownerName,double amount,String pinCode,String accountType,LocalDate maturityDate){

        System.out.println("Maturity date: " + maturityDate);

        FixedDepositAccount newFixedDepositAccount = new FixedDepositAccount(Utils.generateAccountNumber(), ownerName, amount, pinCode, maturityDate);
        AccountList.fixedDepositAccounts.add(newFixedDepositAccount);

        return newFixedDepositAccount.getTransactionHistory().getRecent();
    }
    public String deposit(String accountNumber,double amount,String pinCode,String accountType) {

        if (accountType.equals("Savings")) {
            for (SavingsAccount account : AccountList.savingsAccounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    if(account.verifyPinCode(pinCode)){
                        account.deposit(amount);
                        return account.getTransactionHistory().getRecent();
                    }else {
                        return "Invalid PIN Code";
                    }


                }
            }
            return "Invalid account.";
        }else{
            for (CurrentAccount account : AccountList.currentAccounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    if(account.verifyPinCode(pinCode)){
                        account.deposit(amount);
                        return account.getTransactionHistory().getRecent();
                    }else {
                        return "Invalid PIN Code";
                    }
                }
            }
            return "Invalid account.";
        }

    };

    public String withdraw(String accountNumber,double amount,String pinCode,String accountType) {

        if (accountType.equals("Savings")) {
            for (SavingsAccount account : AccountList.savingsAccounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    if(account.verifyPinCode(pinCode)){
                        account.withdraw(amount);
                        return account.getTransactionHistory().getRecent();
                    }else {
                        return "Invalid PIN Code";
                    }
                }
            }
            return "Invalid account.";
        }
        else if (accountType.equals("Fixed")) {
            for (FixedDepositAccount account : AccountList.fixedDepositAccounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    if(account.verifyPinCode(pinCode)){
                        account.withdraw(amount);
                        return account.getTransactionHistory().getRecent();
                    }else {
                        return "Invalid PIN Code";
                    }
                }
            }
            return "Invalid account.";
        }else{
            for (CurrentAccount account : AccountList.currentAccounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    if(account.verifyPinCode(pinCode)){
                        account.withdraw(amount);
                        return account.getTransactionHistory().getRecent();
                    }else {
                        return "Invalid PIN Code";
                    }
                }
            }
            return "Invalid account.";
        }

    };

    public BankAccount getAccountDetails(String accountNumber) {
        for (CurrentAccount account : AccountList.currentAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        for (SavingsAccount account : AccountList.savingsAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        for (FixedDepositAccount account : AccountList.fixedDepositAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }


        return new CurrentAccount("N/A","N/A",0,"N/A");
    };

}
