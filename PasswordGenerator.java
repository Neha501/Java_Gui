import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;

public class PasswordGenerator extends JFrame implements ActionListener {

    private JTextField passwordField;
    private JSpinner lengthSpinner;

    private JCheckBox uppercaseBox;
    private JCheckBox lowercaseBox;
    private JCheckBox numberBox;
    private JCheckBox symbolBox;

    private JButton generateButton;
    private JButton clearButton;

    private JLabel strengthLabel;

    private final SecureRandom random = new SecureRandom();

    public PasswordGenerator() {

        setTitle("Password Generator");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 1, 10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 40, 25, 40)
        );

        JLabel titleLabel = new JLabel(
                "PASSWORD GENERATOR",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        mainPanel.add(titleLabel);

        JPanel lengthPanel = new JPanel();

        lengthPanel.add(
                new JLabel("Password Length:")
        );

        lengthSpinner = new JSpinner(
                new SpinnerNumberModel(12, 4, 30, 1)
        );

        lengthPanel.add(lengthSpinner);

        mainPanel.add(lengthPanel);

        JPanel optionPanel1 = new JPanel();

        uppercaseBox = new JCheckBox("Uppercase");
        lowercaseBox = new JCheckBox("Lowercase");

        uppercaseBox.setSelected(true);
        lowercaseBox.setSelected(true);

        optionPanel1.add(uppercaseBox);
        optionPanel1.add(lowercaseBox);

        mainPanel.add(optionPanel1);

        JPanel optionPanel2 = new JPanel();

        numberBox = new JCheckBox("Numbers");
        symbolBox = new JCheckBox("Symbols");

        numberBox.setSelected(true);

        optionPanel2.add(numberBox);
        optionPanel2.add(symbolBox);

        mainPanel.add(optionPanel2);

        passwordField = new JTextField();

        passwordField.setEditable(false);

        passwordField.setHorizontalAlignment(
                JTextField.CENTER
        );

        passwordField.setFont(
                new Font("Monospaced", Font.BOLD, 18)
        );

        mainPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();

        generateButton = new JButton("Generate");
        clearButton = new JButton("Clear");

        generateButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(generateButton);
        buttonPanel.add(clearButton);

        mainPanel.add(buttonPanel);

        strengthLabel = new JLabel(
                "Password Strength: -",
                SwingConstants.CENTER
        );

        strengthLabel.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        mainPanel.add(strengthLabel);

        JLabel infoLabel = new JLabel(
                "Select options and generate a password",
                SwingConstants.CENTER
        );

        mainPanel.add(infoLabel);

        add(mainPanel);

        setVisible(true);
    }

    private void generatePassword() {

        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()_+-=";

        String characters = "";

        if (uppercaseBox.isSelected()) {
            characters += uppercase;
        }

        if (lowercaseBox.isSelected()) {
            characters += lowercase;
        }

        if (numberBox.isSelected()) {
            characters += numbers;
        }

        if (symbolBox.isSelected()) {
            characters += symbols;
        }

        if (characters.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select at least one option.",
                    "No Option Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int length = (int) lengthSpinner.getValue();

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {

            int index = random.nextInt(
                    characters.length()
            );

            password.append(
                    characters.charAt(index)
            );
        }

        passwordField.setText(
                password.toString()
        );

        updateStrength(length);
    }

    private void updateStrength(int length) {

        int selectedOptions = 0;

        if (uppercaseBox.isSelected()) {
            selectedOptions++;
        }

        if (lowercaseBox.isSelected()) {
            selectedOptions++;
        }

        if (numberBox.isSelected()) {
            selectedOptions++;
        }

        if (symbolBox.isSelected()) {
            selectedOptions++;
        }

        if (length >= 12 && selectedOptions >= 3) {

            strengthLabel.setText(
                    "Password Strength: Strong"
            );

        } else if (length >= 8 && selectedOptions >= 2) {

            strengthLabel.setText(
                    "Password Strength: Medium"
            );

        } else {

            strengthLabel.setText(
                    "Password Strength: Weak"
            );
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == generateButton) {

            generatePassword();
        }

        else if (e.getSource() == clearButton) {

            passwordField.setText("");

            lengthSpinner.setValue(12);

            uppercaseBox.setSelected(true);
            lowercaseBox.setSelected(true);
            numberBox.setSelected(true);
            symbolBox.setSelected(false);

            strengthLabel.setText(
                    "Password Strength: -"
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new PasswordGenerator()
        );
    }
}