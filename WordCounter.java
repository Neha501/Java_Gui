import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WordCounter extends JFrame implements ActionListener {

    private JTextArea textArea;

    private JLabel wordLabel;
    private JLabel characterLabel;
    private JLabel characterNoSpaceLabel;
    private JLabel lineLabel;

    private JButton countButton;
    private JButton clearButton;

    public WordCounter() {

        setTitle("Word & Character Counter");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        );

        JLabel titleLabel = new JLabel(
                "WORD & CHARACTER COUNTER",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        textArea = new JTextArea();

        textArea.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Enter Your Text"
                )
        );

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(
                new GridLayout(6, 2, 10, 10)
        );

        bottomPanel.add(new JLabel("Words:"));
        wordLabel = new JLabel("0");
        bottomPanel.add(wordLabel);

        bottomPanel.add(new JLabel("Characters:"));
        characterLabel = new JLabel("0");
        bottomPanel.add(characterLabel);

        bottomPanel.add(
                new JLabel("Characters Without Spaces:")
        );

        characterNoSpaceLabel = new JLabel("0");
        bottomPanel.add(characterNoSpaceLabel);

        bottomPanel.add(new JLabel("Lines:"));
        lineLabel = new JLabel("0");
        bottomPanel.add(lineLabel);

        countButton = new JButton("Count");
        clearButton = new JButton("Clear");

        countButton.addActionListener(this);
        clearButton.addActionListener(this);

        bottomPanel.add(countButton);
        bottomPanel.add(clearButton);

        bottomPanel.add(new JLabel(""));
        bottomPanel.add(new JLabel(""));

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == countButton) {

            String text = textArea.getText();

            int characters = text.length();

            String withoutSpaces =
                    text.replaceAll("\\s", "");

            int charactersWithoutSpaces =
                    withoutSpaces.length();

            int words;

            if (text.trim().isEmpty()) {
                words = 0;
            } else {
                words = text.trim()
                        .split("\\s+").length;
            }

            int lines;

            if (text.isEmpty()) {
                lines = 0;
            } else {
                lines = text.split("\\R", -1).length;
            }

            wordLabel.setText(
                    String.valueOf(words)
            );

            characterLabel.setText(
                    String.valueOf(characters)
            );

            characterNoSpaceLabel.setText(
                    String.valueOf(charactersWithoutSpaces)
            );

            lineLabel.setText(
                    String.valueOf(lines)
            );
        }

        if (e.getSource() == clearButton) {

            textArea.setText("");

            wordLabel.setText("0");
            characterLabel.setText("0");
            characterNoSpaceLabel.setText("0");
            lineLabel.setText("0");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new WordCounter()
        );
    }
}