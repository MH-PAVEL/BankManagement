public class ATM {
    private Bank bank;

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public boolean deposit(String accountNumber, double amount) {
        return bank.deposit(accountNumber, amount);
    }

    public boolean withdraw(String accountNumber, double amount) {
        return bank.withdraw(accountNumber, amount);
    }

    public double checkBalance(String accountNumber) {
        return bank.checkBalance(accountNumber);
    }
}
