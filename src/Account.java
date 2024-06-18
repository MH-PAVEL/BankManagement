public class Account {
    private String accountNumber;
    private String ownerName;
    private String nidNumber;
    private String dateOfBirth;
    private double balance;
    private String password;

    public Account(String accountNumber, String ownerName, String nidNumber, String dateOfBirth, double balance, String password) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.nidNumber = nidNumber;
        this.dateOfBirth = dateOfBirth;
        this.balance = balance;
        this.password = password;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getNidNumber() {
        return nidNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public double getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

    // Method to deposit money
    public void deposit(double amount) {
        this.balance += amount;
    }

    // Method to withdraw money
    public boolean withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return accountNumber + "," + ownerName + "," + nidNumber + "," + dateOfBirth + "," + balance + "," + password;
    }
}
