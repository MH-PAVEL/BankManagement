
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String FILE_NAME = "accounts.txt";

    public static List<Account> loadAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String accountNumber = parts[0];
                    String ownerName = parts[1];
                    String nidNumber = parts[2];
                    String dateOfBirth = parts[3];
                    double balance = Double.parseDouble(parts[4]);
                    String password = parts[5];
                    accounts.add(new Account(accountNumber, ownerName, nidNumber, dateOfBirth, balance, password));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public static void saveAccount(Account account) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(account.getAccountNumber() + "," +
                    account.getOwnerName() + "," +
                    account.getNidNumber() + "," +
                    account.getDateOfBirth() + "," +
                    account.getBalance() + "," +
                    account.getPassword() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateAccount(Account account) {
        List<Account> accounts = loadAccounts();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Account acc : accounts) {
                if (acc.getAccountNumber().equals(account.getAccountNumber())) {
                    writer.write(account.getAccountNumber() + "," +
                            account.getOwnerName() + "," +
                            account.getNidNumber() + "," +
                            account.getDateOfBirth() + "," +
                            account.getBalance() + "," +
                            account.getPassword() + "\n");
                } else {
                    writer.write(acc.getAccountNumber() + "," +
                            acc.getOwnerName() + "," +
                            acc.getNidNumber() + "," +
                            acc.getDateOfBirth() + "," +
                            acc.getBalance() + "," +
                            acc.getPassword() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
