
import javax.swing.*;
import java.awt.*;

public class AccountManagementPanel extends JPanel {
    private JTextArea accountDetails;
    private String currentAccountNumber;
    private BankGUI bankGUI;

    public AccountManagementPanel(BankGUI bankGUI) {
        this.bankGUI = bankGUI;

        setLayout(new BorderLayout());

        accountDetails = new JTextArea();
        accountDetails.setEditable(false);
        accountDetails.setMargin(new Insets(10, 10, 10, 10)); // Add padding
        accountDetails.setFont(new Font("Serif", Font.PLAIN, 16)); // Increase font size

        add(new JScrollPane(accountDetails), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Serif", Font.PLAIN, 16));
        depositButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(bankGUI, "Enter deposit amount:", "Deposit", JOptionPane.PLAIN_MESSAGE);
            if (amountStr != null && !amountStr.trim().isEmpty()) {
                double amount = Double.parseDouble(amountStr.trim());
                bankGUI.getBank().deposit(currentAccountNumber, amount);
                displayAccountDetails(currentAccountNumber);
                JOptionPane.showMessageDialog(bankGUI, "Deposit successful!");
            }
        });

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Serif", Font.PLAIN, 16));
        withdrawButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(bankGUI, "Enter withdrawal amount:", "Withdraw", JOptionPane.PLAIN_MESSAGE);
            if (amountStr != null && !amountStr.trim().isEmpty()) {
                double amount = Double.parseDouble(amountStr.trim());
                if (bankGUI.getBank().withdraw(currentAccountNumber, amount)) {
                    displayAccountDetails(currentAccountNumber);
                    JOptionPane.showMessageDialog(bankGUI, "Withdrawal successful!");
                } else {
                    JOptionPane.showMessageDialog(bankGUI, "Insufficient balance!");
                }
            }
        });

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setFont(new Font("Serif", Font.PLAIN, 16));
        signOutButton.addActionListener(e -> {
            bankGUI.showHomePanel();
        });

        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(signOutButton);

        add(buttonPanel, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void displayAccountDetails(String accountNumber) {
        this.currentAccountNumber = accountNumber;
        Account account = bankGUI.getBank().getAccount(accountNumber);
        if (account != null) {
            accountDetails.setText("Account Information:\n\n"
                    + "Account Number: " + account.getAccountNumber() + "\n"
                    + "Owner Name: " + account.getOwnerName() + "\n"
                    + "NID Number: " + account.getNidNumber() + "\n"
                    + "Date of Birth: " + account.getDateOfBirth() + "\n"
                    + "Balance: " + account.getBalance() + "\n");
        } else {
            accountDetails.setText("Account not found.");
        }
    }
}
