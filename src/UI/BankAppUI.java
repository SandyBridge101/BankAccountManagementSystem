package UI;

import DataStructures.TransactionNode;
import Models.BankAccount;
import Models.SavingsAccount;
import UseCases.UseCases;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BankAppUI extends Application {




    UseCases useCases=new UseCases();




    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bank Account Management System");

        TabPane tabPane = new TabPane();

        Tab createAccountTab = new Tab("Create Account", createAccountPane());
        Tab savingsTab = new Tab("Savings Account", accountActionPane("Savings"));
        Tab currentTab = new Tab("Current Account", accountActionPane("Current"));
        Tab fixedTab = new Tab("Fixed Deposit Account", accountActionPane("Fixed"));
        Tab historyTab = new Tab("Account Details", accountDetailsPane());

        tabPane.getTabs().addAll(createAccountTab, savingsTab, currentTab, fixedTab, historyTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabPane, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createAccountPane() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));


        Label nameLabel = new Label("Enter Account Holder Name:");
        TextField nameInput = new TextField();


        Label typeLabel = new Label("Select Account Type:");
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Savings", "Current", "Fixed");

        Label amountLabel = new Label("Enter Initial Deposit Amount (Savings Accounts start from $100):");
        TextField amountInput = new TextField();

        Label dateLabel = new Label("Select maturity Date:");
        DatePicker datePicker = new DatePicker();// Create date field
        Label selectedDateLabel = new Label("Select date (YYYY-MM-DD)");

        datePicker.setValue(LocalDate.now());     // Set default to today

        datePicker.setOnAction(e -> {
            LocalDate selectedDate = datePicker.getValue();
            selectedDateLabel.setText(selectedDate.toString());
            System.out.println("Selected date: " + selectedDate);
        });

        Label pinLabel = new Label("Enter Account Pin:");
        TextField pinInput = new TextField();

        Button createButton = new Button("Create Account");
        Label feedback = new Label();

        createButton.setOnAction(e -> {
            String name = nameInput.getText();
            String type = typeCombo.getValue();
            String pin = pinInput.getText();
            if (name.isEmpty() || type == null) {
                feedback.setText("Please enter a name and select an account type.");

            } else {
                if (!pinInput.getText().matches("\\d{4}")) {
                    feedback.setText("Invalid Pin");
                } else {
                    try {
                        double amount=Double.parseDouble(amountInput.getText());
                        //accounts.put(key, 0.0);
                        //transactionHistory.put(key, new ArrayList<>());
                        String status="";
                        if(type.equals("Fixed")){
                            status=useCases.createAccount(name,amount,pin,type, datePicker.getValue());
                        }else{
                            status=useCases.createAccount(name,amount,pin,type);
                        }
                        feedback.setText(status);

                    }catch (NumberFormatException ex) {
                        feedback.setText("Invalid amount.");
                    }

                }
            }
        });

        layout.getChildren().addAll(nameLabel, nameInput, typeLabel, typeCombo,amountLabel,amountInput,dateLabel, new HBox(datePicker,selectedDateLabel),pinLabel,pinInput, createButton, feedback);
        return layout;
    }

    private VBox accountActionPane(String type) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label nameLabel = new Label("Enter Account Number:");
        TextField accountInput = new TextField();

        Label amountLabel = new Label("Enter Amount:");
        TextField amountInput = new TextField();

        Label pinLabel = new Label("Enter Pin:");
        TextField pinInput = new TextField();

        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Label feedback = new Label();

        depositButton.setOnAction(e -> {
            String status="";

            try {
                double amount = Double.parseDouble(amountInput.getText());
                status=useCases.deposit(accountInput.getText(),amount,pinInput.getText(),type);
                feedback.setText(status);
            } catch (NumberFormatException ex) {
                feedback.setText("Invalid amount.");
            }

        });

        withdrawButton.setOnAction(e -> {
            String status="";

            try {
                double amount = Double.parseDouble(amountInput.getText());
                status=useCases.withdraw(accountInput.getText(),amount,pinInput.getText(),type);
                feedback.setText(status);
            } catch (NumberFormatException ex) {
                feedback.setText("Invalid amount.");
            }

        });

        layout.getChildren().addAll(nameLabel, accountInput, amountLabel, amountInput,pinLabel,pinInput,depositButton, withdrawButton, feedback);
        return layout;
    }

    private VBox accountDetailsPane() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label accountLabel = new Label("Enter Account Number:");
        TextField accountInput = new TextField();
        Label pinLabel = new Label("Enter Account Pin:");
        TextField pinInput = new TextField();
        Label accountNumberLabel = new Label("");
        Label accountOwnerLabel = new Label("");
        Label accountBalanceLabel = new Label("");
        Button viewButton = new Button("View Account Details");
        TextArea historyArea = new TextArea();
        historyArea.setEditable(false);

        viewButton.setOnAction(e -> {
            BankAccount account= useCases.getAccountDetails(accountInput.getText());
            accountNumberLabel.setText("Account Number: "+account.getAccountNumber());
            accountOwnerLabel.setText("User Name:"+account.getOwnerName());
            accountBalanceLabel.setText("Balance: "+account.getBalance());

            TransactionNode head=account.getTransactionHistory().head;

            StringBuilder sb = new StringBuilder();

            TransactionNode current = head;
            if(head.next==null){
                historyArea.setText("Account does not exist");
            }
            int counter = 1;
            while (current != null) {
                sb.append(counter).append(" ").append(current.data).append("\n");
                current = current.next;
                counter++;
            }
            historyArea.setText(sb.toString());


        });

        layout.getChildren().addAll(accountLabel, accountInput,pinLabel,pinInput, viewButton, accountNumberLabel,accountOwnerLabel,accountBalanceLabel,historyArea);
        return layout;
    }
}
