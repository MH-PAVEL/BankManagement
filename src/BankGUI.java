

import javax.swing.*;
import java.awt.*;

public class BankGUI extends JFrame {
    private Bank bank;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private HomePanel homePanel;
    private CreateAccountPanel createAccountPanel;
    private AccountCreatedPanel accountCreatedPanel;
    private SignInPanel signInPanel;
    private AccountManagementPanel accountManagementPanel;

    public BankGUI() {
        bank = new Bank();

        setTitle("Amader Bank");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        homePanel = new HomePanel(this);
        createAccountPanel = new CreateAccountPanel(this);
        accountCreatedPanel = new AccountCreatedPanel(this);
        signInPanel = new SignInPanel(this);
        accountManagementPanel = new AccountManagementPanel(this);

        mainPanel.add(homePanel, "Home");
        mainPanel.add(createAccountPanel, "CreateAccount");
        mainPanel.add(accountCreatedPanel, "AccountCreated");
        mainPanel.add(signInPanel, "SignIn");
        mainPanel.add(accountManagementPanel, "AccountManagement");

        add(mainPanel);
        cardLayout.show(mainPanel, "Home");
    }

    public void showHomePanel() {
        cardLayout.show(mainPanel, "Home");
    }

    public void showCreateAccountPanel() {
        createAccountPanel.clearFields();
        cardLayout.show(mainPanel, "CreateAccount");
    }

    public void showAccountCreatedPanel(String accountNumber, String ownerName, String nidNumber, String dob) {
        accountCreatedPanel.displayAccountDetails(accountNumber, ownerName, nidNumber, dob);
        cardLayout.show(mainPanel, "AccountCreated");
    }

    public void showSignInPanel() {
        signInPanel.clearFields();
        cardLayout.show(mainPanel, "SignIn");
    }

    public void showAccountManagementPanel(String accountNumber) {
        accountManagementPanel.displayAccountDetails(accountNumber);
        cardLayout.show(mainPanel, "AccountManagement");
    }

    public Bank getBank() {
        return bank;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankGUI().setVisible(true));
    }
}
