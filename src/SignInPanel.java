

import javax.swing.*;
import java.awt.*;

public class SignInPanel extends JPanel {
    private JTextField accountNumberField;
    private JPasswordField passwordField;
    private JLabel messageLabel;
    private BankGUI bankGUI;

    public SignInPanel(BankGUI bankGUI) {
        this.bankGUI = bankGUI;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font font = new Font("Serif", Font.PLAIN, 18);

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberLabel.setFont(font);
        accountNumberField = new JTextField();
        accountNumberField.setFont(font);
        accountNumberField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(accountNumberLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(accountNumberField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(font);
        passwordField = new JPasswordField();
        passwordField.setFont(font);
        passwordField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        messageLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(messageLabel, gbc);

        JButton signInButton = new JButton("Sign In");
        signInButton.setFont(font);
        signInButton.addActionListener(e -> {
            System.out.println("Sign In button clicked"); // Debugging statement
            String accountNumber = accountNumberField.getText();
            String password = new String(passwordField.getPassword());
            System.out.println("Account Number entered: " + accountNumber); // Debugging statement
            System.out.println("Password entered: " + password); // Debugging statement

            Account account = bankGUI.getBank().getAccount(accountNumber);
            if (account != null) {
                System.out.println("Account found: " + account.getAccountNumber()); // Debugging statement
                if (account.getPassword().equals(password)) {
                    System.out.println("Password matched"); // Debugging statement
                    bankGUI.showAccountManagementPanel(accountNumber);
                } else {
                    System.out.println("Password did not match"); // Debugging statement
                    messageLabel.setText("Invalid account number or password.");
                }
            } else {
                System.out.println("Account not found"); // Debugging statement
                messageLabel.setText("Invalid account number or password.");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(signInButton, gbc);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(font);
        cancelButton.addActionListener(e -> {
            System.out.println("Cancel button clicked"); // Debugging statement
            bankGUI.showHomePanel();
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(cancelButton, gbc);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void clearFields() {
        accountNumberField.setText("");
        passwordField.setText("");
        messageLabel.setText("");
    }
}
