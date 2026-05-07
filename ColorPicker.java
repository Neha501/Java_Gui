import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColorPicker extends JFrame implements ActionListener {

    private JPanel previewPanel;
    private JButton chooseButton;
    private JButton resetButton;
    private JLabel colorLabel;

    public ColorPicker() {

        setTitle("Color Picker");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel(
                "COLOR PICKER",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 25)
        );

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(20, 10, 10, 10)
        );

        add(titleLabel, BorderLayout.NORTH);

        previewPanel = new JPanel();
        previewPanel.setBackground(Color.WHITE);

        previewPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Color Preview"
                )
        );

        add(previewPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(
                new GridLayout(2, 1, 10, 10)
        );

        JPanel buttonPanel = new JPanel();

        chooseButton = new JButton("Choose Color");
        resetButton = new JButton("Reset");

        chooseButton.addActionListener(this);
        resetButton.addActionListener(this);

        buttonPanel.add(chooseButton);
        buttonPanel.add(resetButton);

        colorLabel = new JLabel(
                "Selected Color: White",
                SwingConstants.CENTER
        );

        colorLabel.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        bottomPanel.add(buttonPanel);
        bottomPanel.add(colorLabel);

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 20, 10)
        );

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == chooseButton) {

            Color selectedColor = JColorChooser.showDialog(
                    this,
                    "Select a Color",
                    previewPanel.getBackground()
            );

            if (selectedColor != null) {

                previewPanel.setBackground(selectedColor);

                int red = selectedColor.getRed();
                int green = selectedColor.getGreen();
                int blue = selectedColor.getBlue();

                String hex = String.format(
                        "#%02X%02X%02X",
                        red,
                        green,
                        blue
                );

                colorLabel.setText(
                        "Selected Color: " + hex +
                        " | RGB(" + red + ", " +
                        green + ", " + blue + ")"
                );
            }
        }

        if (e.getSource() == resetButton) {

            previewPanel.setBackground(Color.WHITE);

            colorLabel.setText(
                    "Selected Color: White"
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new ColorPicker()
        );
    }
}