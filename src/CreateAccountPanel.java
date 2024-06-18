
import javax.swing.*;
import java.awt.*;

public class CreateAccountPanel extends JPanel {
    private JTextField ownerNameField, nidNumberField, dobField;
    private JPasswordField passwordField;
    private BankGUI bankGUI;

    public CreateAccountPanel(BankGUI bankGUI) {
        this.bankGUI = bankGUI;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Serif", Font.PLAIN, 18);
        Font fieldFont = new Font("Serif", Font.PLAIN, 16);

        JLabel ownerNameLabel = new JLabel("Owner Name:");
        ownerNameLabel.setFont(labelFont);
        ownerNameField = new JTextField();
        ownerNameField.setFont(fieldFont);
        ownerNameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(ownerNameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(ownerNameField, gbc);

        JLabel nidNumberLabel = new JLabel("NID Number:");
        nidNumberLabel.setFont(labelFont);
        nidNumberField = new JTextField();
        nidNumberField.setFont(fieldFont);
        nidNumberField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(nidNumberLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(nidNumberField, gbc);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(labelFont);
        dobField = new JTextField();
        dobField.setFont(fieldFont);
        dobField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(dobLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(dobField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        passwordField = new JPasswordField();
        passwordField.setFont(fieldFont);
        passwordField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(labelFont);
        submitButton.addActionListener(e -> {
            String ownerName = ownerNameField.getText();
            String nidNumber = nidNumberField.getText();
            String dob = dobField.getText();
            String password = new String(passwordField.getPassword());

            String accountNumber = bankGUI.getBank().createAccount(ownerName, nidNumber, dob, 0, password);
            bankGUI.showAccountCreatedPanel(accountNumber, ownerName, nidNumber, dob);
            showInitialDepositDialog(bankGUI, accountNumber);
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(labelFont);
        cancelButton.addActionListener(e -> {
            clearFields();
            bankGUI.showHomePanel();
        });
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(cancelButton, gbc);

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
