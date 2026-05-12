import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class NotesApp extends JFrame implements ActionListener {

    private JTextArea textArea;

    private JButton openButton;
    private JButton saveButton;
    private JButton clearButton;

    public NotesApp() {

        setTitle("Simple Notes App");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("SIMPLE NOTES APP", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        openButton = new JButton("Open");
        saveButton = new JButton("Save");
        clearButton = new JButton("Clear");

        openButton.addActionListener(this);
        saveButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == saveButton) {

            JFileChooser chooser = new JFileChooser();

            int option = chooser.showSaveDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {

                File file = chooser.getSelectedFile();

                try {

                    FileWriter writer = new FileWriter(file);

                    writer.write(textArea.getText());

                    writer.close();

                    JOptionPane.showMessageDialog(
                            this,
                            "File Saved Successfully!"
                    );

                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Error Saving File."
                    );
                }
            }
        }

        else if (e.getSource() == openButton) {

            JFileChooser chooser = new JFileChooser();

            int option = chooser.showOpenDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {

                File file = chooser.getSelectedFile();

                try {

                    BufferedReader reader =
                            new BufferedReader(new FileReader(file));

                    textArea.setText("");

                    String line;

                    while ((line = reader.readLine()) != null) {

                        textArea.append(line + "\n");

                    }

                    reader.close();

                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Error Opening File."
                    );
                }
            }
        }

        else if (e.getSource() == clearButton) {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Clear all notes?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                textArea.setText("");

            }
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new NotesApp());

    }
}