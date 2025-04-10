package app;

import Models.*;
import UI.BankAppUI;
import java.time.LocalDate;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Main  {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        SavingsAccount savingsAccount=new SavingsAccount("125","Tariq",200,"1336");
        FixedDepositAccount fixedDepositAccount = new FixedDepositAccount("126","Tariq",200,"1336", LocalDate.of(2025, 12, 31));
        CurrentAccount currentAccount=new CurrentAccount("129","Tariq",200,"1336");

        System.out.println("Savings Account");
        savingsAccount.deposit(10);
        savingsAccount.withdraw(50);
        System.out.println(savingsAccount.getBalance());
        System.out.println(savingsAccount.getAccountNumber());
        System.out.println(savingsAccount.getOwnerName());
        savingsAccount.getTransactionHistory().display();
        savingsAccount.getTransactionHistory().removeAt(0);
        savingsAccount.getTransactionHistory().display();

        fixedDepositAccount.deposit(10);
        fixedDepositAccount.withdraw(150);
        currentAccount.deposit(10);
        currentAccount.withdraw(150);
        Application.launch(BankAppUI.class,args);
    }

}
