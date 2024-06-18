import javax.swing.*;
import java.awt.*;

public class CreateAccountPanel extends JPanel {
    private JTextField ownerNameField, nidNumberField, dobField;
    private JPasswordField passwordField;

    public CreateAccountPanel(BankGUI bankGUI) {
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel ownerNameLabel = new JLabel("Owner Name:");
        ownerNameField = new JTextField();
        add(ownerNameLabel);
        add(ownerNameField);

        JLabel nidNumberLabel = new JLabel("NID Number:");
        nidNumberField = new JTextField();
        add(nidNumberLabel);
        add(nidNumberField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobField = new JTextField();
        add(dobLabel);
        add(dobField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        add(passwordLabel);
        add(passwordField);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(e -> {
            clearFields();
            bankGUI.showHomePanel();
        });

        submitButton.addActionListener(e -> {
            String ownerName = ownerNameField.getText();
            String nidNumber = nidNumberField.getText();
            String dob = dobField.getText();
            String password = new String(passwordField.getPassword());

            String accountNumber = bankGUI.getBank().createAccount(ownerName, nidNumber, dob, 0, password);

            bankGUI.showAccountCreatedPanel(accountNumber, ownerName, nidNumber, dob);
            showInitialDepositDialog(bankGUI, accountNumber);
        });

        add(submitButton);
        add(cancelButton);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void clearFields() {
        ownerNameField.setText("");
        nidNumberField.setText("");
        dobField.setText("");
        passwordField.setText("");
    }

    private void showInitialDepositDialog(BankGUI bankGUI, String accountNumber) {
        String amountStr = JOptionPane.showInputDialog(this, "Enter initial deposit amount:", "Initial Deposit", JOptionPane.PLAIN_MESSAGE);
        if (amountStr != null && !amountStr.trim().isEmpty()) {
            double amount = Double.parseDouble(amountStr.trim());
            bankGUI.getBank().deposit(accountNumber, amount);
            JOptionPane.showMessageDialog(this, "Initial deposit successful!");

            // Show additional message with account number and deposited amount
            JOptionPane.showMessageDialog(bankGUI,
                    "Your account is created successfully and your account number is: " + accountNumber + "\nYour deposited initial balance is: " + amount + "\nUse it further to sign in.",
                    "Account Created",
                    JOptionPane.INFORMATION_MESSAGE);

            bankGUI.showHomePanel();
        }
    }
}
