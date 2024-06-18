
import java.util.HashMap;
import java.util.List;

public class Bank {
    private HashMap<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
        loadAccounts();
    }

    private void loadAccounts() {
        List<Account> accountList = Database.loadAccounts();
        for (Account account : accountList) {
            accounts.put(account.getAccountNumber(), account);
        }
    }

    public String createAccount(String ownerName, String nidNumber, String dateOfBirth, double initialBalance, String password) {
        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber, ownerName, nidNumber, dateOfBirth, initialBalance, password);
        accounts.put(accountNumber, account);
        Database.saveAccount(account); // Save the new account
        return accountNumber;
    }

    private String generateAccountNumber() {
        for (int i = 1; i <= 9999; i++) {
            String accountNumber = String.format("%04d", i);
            if (!accounts.containsKey(accountNumber)) {
                return accountNumber;
            }
        }
        throw new RuntimeException("No available account numbers.");
    }

    public boolean deposit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            Database.updateAccount(account); // Update the account information in the database
            return true;
        }
        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.withdraw(amount)) {
            Database.updateAccount(account); // Update the account information in the database
            return true;
        }
        return false;
    }

    public double checkBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            return account.getBalance();
        }
        return -1;
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}
