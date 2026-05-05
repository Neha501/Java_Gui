import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.*;

public class AgeCalculator extends JFrame implements ActionListener {

    private JTextField dayField;
    private JTextField monthField;
    private JTextField yearField;

    private JLabel resultLabel;

    private JButton calculateButton;
    private JButton clearButton;

    public AgeCalculator() {

        setTitle("Age Calculator");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 2, 10, 15));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        );

        JLabel titleLabel = new JLabel(
                "AGE CALCULATOR",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        mainPanel.add(titleLabel);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel("Birth Day:"));
        dayField = new JTextField();
        mainPanel.add(dayField);

        mainPanel.add(new JLabel("Birth Month:"));
        monthField = new JTextField();
        mainPanel.add(monthField);

        mainPanel.add(new JLabel("Birth Year:"));
        yearField = new JTextField();
        mainPanel.add(yearField);

        calculateButton = new JButton("Calculate Age");
        clearButton = new JButton("Clear");

        calculateButton.addActionListener(this);
        clearButton.addActionListener(this);

        mainPanel.add(calculateButton);
        mainPanel.add(clearButton);

        mainPanel.add(new JLabel("Your Age:"));

        resultLabel = new JLabel("Enter your birth date");
        resultLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        mainPanel.add(resultLabel);

        add(mainPanel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == calculateButton) {

            try {

                int day = Integer.parseInt(
                        dayField.getText()
                );

                int month = Integer.parseInt(
                        monthField.getText()
                );

                int year = Integer.parseInt(
                        yearField.getText()
                );

                LocalDate birthDate =
                        LocalDate.of(year, month, day);

                LocalDate currentDate =
                        LocalDate.now();

                if (birthDate.isAfter(currentDate)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Birth date cannot be in the future.",
                            "Invalid Date",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                Period age =
                        Period.between(birthDate, currentDate);

                resultLabel.setText(
                        age.getYears() + " Years, " +
                        age.getMonths() + " Months, " +
                        age.getDays() + " Days"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid birth date.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        if (e.getSource() == clearButton) {

            dayField.setText("");
            monthField.setText("");
            yearField.setText("");

            resultLabel.setText("Enter your birth date");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new AgeCalculator()
        );
    }
}