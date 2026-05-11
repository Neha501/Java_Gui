import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UnitConverter extends JFrame implements ActionListener {

    private JTextField inputField;
    private JTextField outputField;

    private JComboBox<String> conversionBox;

    private JButton convertButton;
    private JButton clearButton;

    public UnitConverter() {

        setTitle("Unit Converter");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 15));

        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel title = new JLabel("UNIT CONVERTER", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        panel.add(title);
        panel.add(new JLabel());

        panel.add(new JLabel("Conversion Type:"));

        String[] conversions = {
                "Kilometers to Miles",
                "Miles to Kilometers",
                "Kilograms to Pounds",
                "Pounds to Kilograms",
                "Celsius to Fahrenheit",
                "Fahrenheit to Celsius"
        };

        conversionBox = new JComboBox<>(conversions);

        panel.add(conversionBox);

        panel.add(new JLabel("Enter Value:"));

        inputField = new JTextField();

        panel.add(inputField);

        panel.add(new JLabel("Result:"));

        outputField = new JTextField();
        outputField.setEditable(false);

        panel.add(outputField);

        convertButton = new JButton("Convert");
        clearButton = new JButton("Clear");

        convertButton.addActionListener(this);
        clearButton.addActionListener(this);

        panel.add(convertButton);
        panel.add(clearButton);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == convertButton) {

            try {

                double value = Double.parseDouble(inputField.getText());

                double result = 0;

                switch (conversionBox.getSelectedIndex()) {

                    case 0:
                        result = value * 0.621371;
                        break;

                    case 1:
                        result = value / 0.621371;
                        break;

                    case 2:
                        result = value * 2.20462;
                        break;

                    case 3:
                        result = value / 2.20462;
                        break;

                    case 4:
                        result = (value * 9 / 5) + 32;
                        break;

                    case 5:
                        result = (value - 32) * 5 / 9;
                        break;
                }

                outputField.setText(String.format("%.2f", result));

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid number.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        if (e.getSource() == clearButton) {

            inputField.setText("");
            outputField.setText("");
            conversionBox.setSelectedIndex(0);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new UnitConverter());
    }
}