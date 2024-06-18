import javax.swing.*;
import java.awt.*;

public class AccountCreatedPanel extends JPanel {
    private JTextArea accountDetails;

    public AccountCreatedPanel(BankGUI bankGUI) {
        setLayout(new BorderLayout());

        accountDetails = new JTextArea();
        accountDetails.setEditable(false);
        accountDetails.setMargin(new Insets(10, 10, 10, 10)); // Add padding
        accountDetails.setFont(new Font("Serif", Font.PLAIN, 16)); // Increase font size

        add(new JScrollPane(accountDetails), BorderLayout.CENTER);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void displayAccountDetails(String accountNumber, String ownerName, String nidNumber, String dob) {
        accountDetails.setText("Account Created Successfully!\n\n"
                + "Account Number: " + accountNumber + "\n"
                + "Owner Name: " + ownerName + "\n"
                + "NID Number: " + nidNumber + "\n"
                + "Date of Birth: " + dob + "\n");
    }
}
