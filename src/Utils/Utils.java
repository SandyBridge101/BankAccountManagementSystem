package Utils;
import DataStructures.AccountList;
import Models.CurrentAccount;
import Models.FixedDepositAccount;
import Models.SavingsAccount;

import java.security.SecureRandom;

public class Utils extends AccountList {
    private static final String CHARACTERS = "0123456789";
    private static final int LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();

    private static String generateWord() {//function to generate 6-word string
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        String accountNumber = sb.toString();
        for (CurrentAccount account : AccountList.currentAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return "";
            }
        }
        for (SavingsAccount account : AccountList.savingsAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return "";
            }
        }
        for (FixedDepositAccount account : AccountList.fixedDepositAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return "";
            }
        }

        return sb.toString();
    }

    public static String generateAccountNumber() {//function to validate and return string as account number
        String generatedAccountNumber = "";
        int iteration=0;
        while (generatedAccountNumber.isEmpty()) {
            generatedAccountNumber = generateWord();
            iteration++;
            if(iteration==1000000000) {//cut operations after 1 billion attempts
                break;
            }
        }
        return generatedAccountNumber;
    }
}
