package banking_system;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Bank<T extends Account<String>> {
    private List<T> accounts = new ArrayList<>();

    public void addAccount(T account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        accounts.add(account);
    }

    public T getAccount(Integer accountNumber) {
        if (accountNumber == null || accountNumber <= 0) {
            throw new IllegalArgumentException("Account number cannot be null or less than or equal to zero");
        }
        for (T account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void depositCheck(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Integer accountNumber = Integer.parseInt(parts[0]);
                    double amount = Double.parseDouble(parts[1]);
                    T account = getAccount(accountNumber);
                    if (account != null) {
                        account.deposit(amount);
                    }
                }
            }
        }
    }

}
