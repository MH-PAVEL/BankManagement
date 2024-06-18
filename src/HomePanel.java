import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel(BankGUI bankGUI) {
        setLayout(new BorderLayout(10, 10));

        JLabel welcomeLabel = new JLabel("Welcome to Amader Bank", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 28));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton signInButton = new JButton("Sign In");
        signInButton.setPreferredSize(new Dimension(200, 30)); // Set size to match "Create An Account"
        signInButton.addActionListener(e -> bankGUI.showSignInPanel());
        buttonPanel.add(signInButton, gbc);

        gbc.gridy++;
        JButton createAccountButton = new JButton("Create An Account");
        createAccountButton.setPreferredSize(new Dimension(200, 30)); // Ensure the size matches
        createAccountButton.addActionListener(e -> bankGUI.showCreateAccountPanel());
        buttonPanel.add(createAccountButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    }
}
