import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMSystem extends JFrame implements ActionListener {

    private JTextField nameField;
    private JTextField amountField;

    private JLabel balanceLabel;

    private JTextArea historyArea;

    private JButton depositButton;
    private JButton withdrawButton;
    private JButton balanceButton;
    private JButton clearButton;

    private double balance = 10000.00;

    public ATMSystem() {

        setTitle("ATM Simulator");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout(10,10));

        JLabel title = new JLabel("ATM SIMULATOR", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(4,2,15,15));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15,25,15,25));

        centerPanel.add(new JLabel("Account Holder:"));

        nameField = new JTextField();
        centerPanel.add(nameField);

        centerPanel.add(new JLabel("Amount (₹):"));

        amountField = new JTextField();
        centerPanel.add(amountField);

        centerPanel.add(new JLabel("Current Balance:"));

        balanceLabel = new JLabel("₹ " + String.format("%.2f", balance));
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(balanceLabel);

        centerPanel.add(new JLabel("Transaction History:"));

        centerPanel.add(new JLabel(""));

        add(centerPanel, BorderLayout.CENTER);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setPreferredSize(new Dimension(600,200));

        add(scrollPane, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        balanceButton = new JButton("Check Balance");
        clearButton = new JButton("Clear History");

        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        balanceButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.AFTER_LAST_LINE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String name = nameField.getText().trim();

        if(name.isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter account holder name."
            );
            return;
        }

        if(e.getSource()==balanceButton){

            JOptionPane.showMessageDialog(
                    this,
                    "Current Balance : ₹" + String.format("%.2f", balance)
            );

            historyArea.append("Balance Checked : ₹"
                    + String.format("%.2f", balance) + "\n");

            return;
        }

        if(e.getSource()==clearButton){

            historyArea.setText("");
            return;
        }

        double amount;

        try{

            amount = Double.parseDouble(amountField.getText());

            if(amount<=0){

                JOptionPane.showMessageDialog(
                        this,
                        "Amount should be greater than zero."
                );
                return;
            }

        }
        catch(Exception ex){

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid amount."
            );
            return;
        }

        if(e.getSource()==depositButton){

            balance += amount;

            historyArea.append(
                    "Deposited ₹"
                    + String.format("%.2f", amount)
                    + " | Balance ₹"
                    + String.format("%.2f", balance)
                    + "\n"
            );

            JOptionPane.showMessageDialog(
                    this,
                    "₹" + amount + " Deposited Successfully."
            );

        }

        else if(e.getSource()==withdrawButton){

            if(amount>balance){

                JOptionPane.showMessageDialog(
                        this,
                        "Insufficient Balance!"
                );
                return;
            }

            balance -= amount;

            historyArea.append(
                    "Withdrawn ₹"
                    + String.format("%.2f", amount)
                    + " | Balance ₹"
                    + String.format("%.2f", balance)
                    + "\n"
            );

            JOptionPane.showMessageDialog(
                    this,
                    "₹" + amount + " Withdrawn Successfully."
            );

        }

        balanceLabel.setText(
                "₹ " + String.format("%.2f", balance)
        );

        amountField.setText("");

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new ATMSystem());

    }
}